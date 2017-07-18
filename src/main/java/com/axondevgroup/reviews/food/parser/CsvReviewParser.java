package com.axondevgroup.reviews.food.parser;

import com.axondevgroup.reviews.food.function.StringToReviewFunction;
import com.axondevgroup.reviews.food.model.Review;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Class for parsing Reviews from CSV file.
 *
 * @author Denys Storozhenko
 */
public class CsvReviewParser {
    private static final StringToReviewFunction STRING_TO_REVIEW_FUNCTION = new StringToReviewFunction();

    private JavaSparkContext sparkContext;

    public CsvReviewParser(JavaSparkContext sparkContext) {
        this.sparkContext = sparkContext;
    }

    public JavaRDD<Review> parse(String fileName) {
        JavaRDD<String> lines = sparkContext.textFile(fileName);

        String header = lines.first();
        return lines
                .filter(elem -> !elem.equals(header)) // skip header
                .map(STRING_TO_REVIEW_FUNCTION);
    }
}
