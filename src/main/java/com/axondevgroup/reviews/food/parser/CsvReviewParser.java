package com.axondevgroup.reviews.food.parser;

import com.axondevgroup.reviews.food.function.StringToReviewFunction;
import com.axondevgroup.reviews.food.model.Review;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.io.Serializable;

/**
 * Class for parsing Reviews from CSV file.
 *
 * @author Denys Storozhenko
 */
public class CsvReviewParser implements Serializable {
    private static final StringToReviewFunction STRING_TO_REVIEW_FUNCTION = new StringToReviewFunction();
    private static final Integer MIN_PARTITIONS = 4;

    private JavaSparkContext sparkContext;

    public CsvReviewParser(JavaSparkContext sparkContext) {
        this.sparkContext = sparkContext;
    }

    public JavaRDD<Review> parse(String fileName) {
        JavaRDD<String> lines = sparkContext.textFile(fileName, MIN_PARTITIONS);

        String header = lines.first();
        return lines
                .filter(elem -> !elem.equals(header)) // skip header
                .map(STRING_TO_REVIEW_FUNCTION);
    }
}
