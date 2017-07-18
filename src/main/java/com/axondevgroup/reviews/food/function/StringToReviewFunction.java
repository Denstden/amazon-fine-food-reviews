package com.axondevgroup.reviews.food.function;

import com.axondevgroup.reviews.food.model.Review;
import org.apache.spark.api.java.function.Function;

import static com.axondevgroup.reviews.food.util.Constants.ZERO;

/**
 * Class for converting String to Review.
 *
 * @author Denys Storozhenko
 */
public class StringToReviewFunction implements Function<String, Review> {
    private static final String SPLIT_PATTERN = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    @Override
    public Review call(String line) throws Exception {
        String[] fields = line.split(SPLIT_PATTERN);
        int index = ZERO;

        Review review = new Review();
        review.setId(Long.valueOf(fields[index++]));
        review.setProductId(fields[index++]);
        review.setUserId(fields[index++]);
        review.setProfileName(fields[index++]);
        review.setHelpfulnessNumerator(Long.valueOf(fields[index++]));
        review.setHelpfulnessDenominator(Long.valueOf(fields[index++]));
        review.setScore(Long.valueOf(fields[index++]));
        review.setTime(Long.valueOf(fields[index++]));
        review.setSummary(fields[index++]);
        review.setText(fields[index]);

        return review;
    }
}
