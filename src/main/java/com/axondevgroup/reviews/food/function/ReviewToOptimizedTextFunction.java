package com.axondevgroup.reviews.food.function;

import com.axondevgroup.reviews.food.model.OptimizedText;
import com.axondevgroup.reviews.food.model.Review;
import org.apache.spark.api.java.function.FlatMapFunction;

import java.util.*;

import static com.axondevgroup.reviews.food.util.Constants.ONE;
import static com.axondevgroup.reviews.food.util.Constants.ONE_THOUSAND;
import static com.axondevgroup.reviews.food.util.Constants.ZERO;

/**
 * Class for converting Review to OptimizedText with optimizations for Google API.
 *
 * @author Denys Storozhenko
 */
public class ReviewToOptimizedTextFunction implements FlatMapFunction<Iterator<Review>, OptimizedText> {
    private static final String PART_SEPARATOR = ".";

    @Override
    public Iterator<OptimizedText> call(Iterator<Review> reviewIterator) throws Exception {
        List<OptimizedText> result = new ArrayList<>();
        Review current;
        StringBuffer text = new StringBuffer();
        int lastIndexOf = ZERO;
        Map<Long, OptimizedText.Describer> reviewId = new HashMap<>();

        while (reviewIterator.hasNext()) {
            current = reviewIterator.next();
            String currentText = current.getText();
            if (currentText.length() > ONE_THOUSAND) {
                splitLongText(result, current, currentText);
            } else {
                if ((text.length() + currentText.length()) > ONE_THOUSAND) {
                    if (text.length() != ZERO) {
                        addOptimizedText(result, text, reviewId);

                        reviewId = new HashMap<>();
                        lastIndexOf = ZERO;
                    }
                    text = new StringBuffer(currentText);
                } else {
                    text.append(currentText);
                }
                reviewId.put(current.getId(), new OptimizedText.Describer(lastIndexOf, lastIndexOf + currentText.length(), ONE));
                lastIndexOf = lastIndexOf + currentText.length();
            }
        }
        if (text.length() != ZERO) {
            addOptimizedText(result, text, reviewId);
        }
        return result.iterator();
    }

    private void addOptimizedText(List<OptimizedText> result, StringBuffer text, Map<Long, OptimizedText.Describer> reviewId) {
        OptimizedText optimizedText;
        optimizedText = new OptimizedText();
        optimizedText.setText(text.toString());
        optimizedText.setReviewId(reviewId);
        result.add(optimizedText);
    }

    private void splitLongText(List<OptimizedText> result, Review current, String currentText) {
        OptimizedText optimizedText;
        int countParts = currentText.length() / ONE_THOUSAND + ONE;
        int currentPart = ONE;
        int beginFrom = ZERO;
        int lastIndexOfPart;
        int substringTo;
        String clipped;
        while (currentPart <= countParts) {
            substringTo = (ONE_THOUSAND * currentPart > currentText.length()) ? currentText.length() : ONE_THOUSAND * currentPart;
            lastIndexOfPart = beginFrom + currentText.substring(beginFrom, substringTo).lastIndexOf(PART_SEPARATOR) + ONE;

            optimizedText = new OptimizedText();
            clipped = (lastIndexOfPart >= beginFrom) ? currentText.substring(beginFrom, lastIndexOfPart) : currentText;
            optimizedText.setText(clipped);

            Map<Long, OptimizedText.Describer> reviewIdMap = new HashMap<>();
            reviewIdMap.put(current.getId(), new OptimizedText.Describer(ZERO, clipped.length(), currentPart));
            optimizedText.setReviewId(reviewIdMap);

            result.add(optimizedText);

            beginFrom = lastIndexOfPart;
            currentPart++;
        }
    }
}
