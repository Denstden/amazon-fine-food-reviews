package com.axondevgroup.reviews.food.function;

import com.axondevgroup.reviews.food.model.Review;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringToReviewFunctionTest {

    private StringToReviewFunction function = new StringToReviewFunction();

    @Test
    public void callShouldParseStringWithCommas() throws Exception {
        // Given
        String input = "70,B000E7VI7S,AWCBF2ZWIN57F,C. Salcido,0,2,5,1185753600,pretzel haven!,this was sooooo deliscious but too bad i ate em too fast and gained 2 pds! my fault";

        // When
        Review review = function.call(input);

        // Then
        assertEquals(70L, (long)review.getId());
        assertEquals("B000E7VI7S", review.getProductId());
        assertEquals("AWCBF2ZWIN57F", review.getUserId());
        assertEquals("C. Salcido", review.getProfileName());
        assertEquals(0L, (long)review.getHelpfulnessNumerator());
        assertEquals(2L, (long)review.getHelpfulnessDenominator());
        assertEquals(5L, (long)review.getScore());
        assertEquals(1185753600L, (long)review.getTime());
        assertEquals("pretzel haven!", review.getSummary());
        assertEquals("this was sooooo deliscious but too bad i ate em too fast and gained 2 pds! my fault", review.getText());
    }

    @Test
    public void callShouldParseStringWithQuotes() throws Exception {
        // Given
        String input = "5,B006K2ZZ7K,A1UQRSCLF8GW1T,\"Michael D. Bigham \"\"M. Wassir\"\"\",0,0,5,1350777600,Great taffy,\"Great taffy at a great price.  There was a wide assortment of yummy taffy.  Delivery was very quick.  If your a taffy lover, this is a deal.\"";

        // When
        Review review = function.call(input);

        // Then
        assertEquals(5L, (long)review.getId());
        assertEquals("B006K2ZZ7K", review.getProductId());
        assertEquals("A1UQRSCLF8GW1T", review.getUserId());
        assertEquals("\"Michael D. Bigham \"\"M. Wassir\"\"\"", review.getProfileName());
        assertEquals(0L, (long)review.getHelpfulnessNumerator());
        assertEquals(0L, (long)review.getHelpfulnessDenominator());
        assertEquals(5L, (long)review.getScore());
        assertEquals(1350777600, (long)review.getTime());
        assertEquals("Great taffy", review.getSummary());
        assertEquals("\"Great taffy at a great price.  There was a wide assortment of yummy taffy.  Delivery was very quick.  If your a taffy lover, this is a deal.\"", review.getText());
    }
}