package com.axondevgroup.reviews.food;

import com.axondevgroup.reviews.food.dto.GoogleTranslateDto;
import com.axondevgroup.reviews.food.function.ReviewToOptimizedTextFunction;
import com.axondevgroup.reviews.food.httpclient.ApiTranslator;
import com.axondevgroup.reviews.food.httpclient.MockedGoogleApiTranslator;
import com.axondevgroup.reviews.food.model.OptimizedText;
import com.axondevgroup.reviews.food.model.Review;
import com.axondevgroup.reviews.food.model.WordWrapper;
import com.axondevgroup.reviews.food.parser.CsvReviewParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Properties;

import static com.axondevgroup.reviews.food.util.Constants.*;
import static org.apache.spark.sql.functions.*;

/**
 * Class for analyzing food reviews by Spark.
 *
 * @author Denys Storozhenko
 */
public class SparkFoodReviewsAnalyzer implements Serializable {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/spark.properties";
    private static final String SPARK_DRIVER_MEMORY = "spark.driver.memory";
    private static final String SPARK_DRIVER_CORES= "spark.driver.cores";
    private static final String SPARK_EXECUTOR_MEMORY = "spark.executor.memory";
    private static final String WORD_SPLIT_PATTERN = "\\W+";
    private static final ReviewToOptimizedTextFunction REVIEW_TO_OPTIMIZED_TEXT_FUNCTION =
            new ReviewToOptimizedTextFunction();

    private JavaSparkContext sparkContext;
    private CsvReviewParser parser;

    public SparkFoodReviewsAnalyzer() throws IOException {
        Configuration hdfsConf = new Configuration();
        FileSystem fs = FileSystem.get(hdfsConf);

        FSDataInputStream is = fs.open(new Path(PATH_TO_PROPERTIES));
        Properties prop = new Properties();
        prop.load(is);

        SparkConf sparkConf = new SparkConf()
                .setAppName(APP_NAME)
                .setMaster(SPARK_MASTER)
                .set(SPARK_DRIVER_MEMORY, prop.getProperty(SPARK_DRIVER_MEMORY))
                .set(SPARK_EXECUTOR_MEMORY, prop.getProperty(SPARK_EXECUTOR_MEMORY))
                .set(SPARK_DRIVER_CORES, prop.getProperty(SPARK_DRIVER_CORES));
        sparkContext = new JavaSparkContext(sparkConf);

        parser = new CsvReviewParser(sparkContext);
    }

    public void analyze(String fileName, boolean needTranslate) {
        JavaRDD<Review> reviews = parser.parse(fileName);

        analyzeReviews(reviews);
        analyzeWordsInReviews(reviews);

        if (needTranslate) {
            JavaRDD<OptimizedText> optimizedTexts = reviews.mapPartitions(REVIEW_TO_OPTIMIZED_TEXT_FUNCTION);

            optimizedTexts.foreachPartition(e -> {
                ApiTranslator translator = new MockedGoogleApiTranslator();
                while (e.hasNext()) {
                    GoogleTranslateDto translate = translator.translate(e.next().getText());
                    System.out.println(translate.getText());
                }
            });
        }
    }

    private void analyzeReviews(JavaRDD<Review> reviews) {
        SQLContext sqlContext = new SQLContext(sparkContext);

        Dataset<Row> dataset = sqlContext.createDataFrame(reviews, Review.class);

        showMostActiveUsers(dataset);
        showMostCommentedFoodItems(dataset);
    }

    private void showMostActiveUsers(Dataset<Row> dataset) {
        System.out.println("1000 most active users: ");
        dataset.groupBy("profileName")
                .count()
                .orderBy(desc("count"), asc("profileName"))
                .limit(ONE_THOUSAND)
                .show(ONE_THOUSAND, false);
    }

    private void showMostCommentedFoodItems(Dataset<Row> dataset) {
        System.out.println("1000 most commented food items: ");
        dataset.groupBy("productId")
                .agg(count("text").alias("cnt"))
                .orderBy(desc("cnt"), asc("productId"))
                .limit(ONE_THOUSAND)
                .show(ONE_THOUSAND, false);
    }

    private void analyzeWordsInReviews(JavaRDD<Review> reviews) {
        JavaRDD<WordWrapper> words = reviews
                .flatMap(e -> Arrays.asList(e.getText().split(WORD_SPLIT_PATTERN)).iterator())
                .filter(word -> word.trim().length() != 0)
                .map(WordWrapper::new);

        SQLContext sqlContext = new SQLContext(sparkContext);
        Dataset<Row> dataFrame = sqlContext.createDataFrame(words, WordWrapper.class);

        System.out.println("1000 most used words in reviews: ");
        dataFrame.groupBy("value")
                .count()
                .orderBy(desc("count"), asc("value"))
                .limit(ONE_THOUSAND)
                .show(ONE_THOUSAND, false);
    }
}
