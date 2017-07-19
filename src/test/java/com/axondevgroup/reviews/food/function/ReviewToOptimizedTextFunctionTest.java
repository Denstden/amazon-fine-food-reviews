package com.axondevgroup.reviews.food.function;

import com.axondevgroup.reviews.food.model.OptimizedText;
import com.axondevgroup.reviews.food.model.Review;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class ReviewToOptimizedTextFunctionTest {

    private final ReviewToOptimizedTextFunction function = new ReviewToOptimizedTextFunction();

    @Test
    public void callShouldCombineTextsAndReturnProperOptimizedTexts() throws Exception {
        // Given
        Review review1 = createReview(1L, "sa das dad asd asd s das");
        Review review2 = createReview(2L, "qweqwwqewqqewq. qweqw");

        Iterator<Review> reviewsIterator = Arrays.asList(review1, review2).iterator();

        // When
        Iterator<OptimizedText> result = function.call(reviewsIterator);

        // Then
        assertTrue(result.hasNext());
        OptimizedText text = result.next();

        assertEquals("sa das dad asd asd s dasqweqwwqewqqewq. qweqw", text.getText());

        Map<Long, OptimizedText.Describer> reviewId = text.getReviewId();
        assertTrue(reviewId.containsKey(1L));
        assertTrue(reviewId.containsKey(2L));

        assertEquals(0, reviewId.get(1L).getIndexFrom());
        assertEquals(24, reviewId.get(1L).getIndexTo());
        assertEquals(1, reviewId.get(1L).getPart());

        assertEquals(24, reviewId.get(2L).getIndexFrom());
        assertEquals(45, reviewId.get(2L).getIndexTo());
        assertEquals(1, reviewId.get(2L).getPart());
    }

    @Test
    public void callShouldSplitTextAndReturnProperOptimizedTexts() throws Exception {
        // Given
        Review reviewWithLongText = createReview(1L, "I know the product title says Molecular Gastronomy, but don't let" +
                " that scare you off.  I have been looking for this for a while now, not for food science, " +
                "but for something more down to earth.  I use it to make my own coffee creamer.<br /><br />I" +
                "have to have my coffee blonde and sweet - but the flavored creamers are full of the bad " +
                "kinds of fat, and honestly, I hate to use manufactured \"\"food\"\" items.  I really don't " +
                "think they are good for the body.  On the other hand, I hate using cold milk or cream, " +
                "because I like HOT coffee.<br /><br />I stumbled across this on Amazon one day and got " +
                "the idea of making my own creamer.  I also bought low-fat (non-instant) milk powder and" +
                " regular milk powder. The non-instant lowfat milk is a little sweeter and tastes fresher" +
                " than regular instant low-fat milk, but does not dissolve good in cold water - which is " +
                "not a problem for hot coffee.  You will have to play with the ratios - I would not do just " +
                "the heavy cream, it made the coffee too rich. Also, I think the powder is too expensive to" +
                " just use on it's own. I like mixing 1/3 of each together.<br /><br />For flavoring, I bough" +
                " cocoa bean powder, vanilla bean powder, and caster (superfine) sugar.  I mix up small batches" +
                " along with spices like cinnamon and nutmeg to make my own flavored creamers.  If you wanted," +
                " you could use a fake sweetner powder instead.  I make up small amounts that I store in jelly" +
                " canning jars. I also use my little food chopper/food processor to blend everything, so the " +
                "sugar is not heavier and sinks to the bottom.  Let it settle for a bit before opening the top" +
                " though.<br /><br />This stuff tastes WAY better than the storebought creamers and it is fun " +
                "to experiment and come up with your own flavors.  I am going to try using some essential oils " +
                "next and see if I can get a good chocolate/orange mix.<br /><br />All of the ingredients I " +
                "mentioned are here online.  Take the time to experiment.  Maybe you don't use any low-fat milk. " +
                "Or don't add any flavorings.  It is up to you.  Also, would make great housewarming/host(ess) gifts." +
                "<br /><br />I am sure other molecular people will be able to tell you more of what you can do with" +
                " it, and I am sure I will experiment with it in cooking - but the main reason I bought it was to make" +
                " my own creamer and it worked out great.");

        Iterator<Review> reviewsIterator = Collections.singletonList(reviewWithLongText).iterator();

        // When
        Iterator<OptimizedText> result = function.call(reviewsIterator);

        // Then
        assertTrue(result.hasNext());
        OptimizedText text1 = result.next();

        assertEquals("I know the product title says Molecular Gastronomy, but don't let that scare you off.  " +
                "I have been looking for this for a while now, not for food science, but for something more down to " +
                "earth.  I use it to make my own coffee creamer.<br /><br />Ihave to have my coffee blonde and sweet - " +
                "but the flavored creamers are full of the bad kinds of fat, and honestly, I hate to use manufactured" +
                " \"\"food\"\" items.  I really don't think they are good for the body.  On the other hand, I hate " +
                "using cold milk or cream, because I like HOT coffee.<br /><br />I stumbled across this on Amazon one " +
                "day and got the idea of making my own creamer.  I also bought low-fat (non-instant) milk powder and " +
                "regular milk powder. The non-instant lowfat milk is a little sweeter and tastes fresher than regular " +
                "instant low-fat milk, but does not dissolve good in cold water - which is not a problem for hot coffee." +
                "  You will have to play with the ratios - I would not do just the heavy cream, it made the coffee too rich.",
                text1.getText());

        Map<Long, OptimizedText.Describer> reviewId = text1.getReviewId();
        assertTrue(reviewId.containsKey(1L));
        assertEquals(1, reviewId.size());
        assertEquals(1, reviewId.get(1L).getPart());

        assertTrue(result.hasNext());
        OptimizedText text2 = result.next();

        assertEquals(" Also, I think the powder is too expensive to just use on it's own. I like mixing 1/3 of " +
                "each together.<br /><br />For flavoring, I bough cocoa bean powder, vanilla bean powder, and caster " +
                "(superfine) sugar.  I mix up small batches along with spices like cinnamon and nutmeg to make my own " +
                "flavored creamers.  If you wanted, you could use a fake sweetner powder instead.  I make up small " +
                "amounts that I store in jelly canning jars. I also use my little food chopper/food processor to blend" +
                " everything, so the sugar is not heavier and sinks to the bottom.  Let it settle for a bit before " +
                "opening the top though.<br /><br />This stuff tastes WAY better than the storebought creamers and " +
                "it is fun to experiment and come up with your own flavors.  I am going to try using some essential " +
                "oils next and see if I can get a good chocolate/orange mix.<br /><br />All of the ingredients I " +
                "mentioned are here online.  Take the time to experiment.  Maybe you don't use any low-fat milk. Or " +
                "don't add any flavorings.", text2.getText());

        Map<Long, OptimizedText.Describer> reviewId2 = text2.getReviewId();
        assertTrue(reviewId2.containsKey(1L));
        assertEquals(1, reviewId2.size());
        assertEquals(2, reviewId2.get(1L).getPart());

        assertTrue(result.hasNext());
        OptimizedText text3 = result.next();

        assertEquals("  It is up to you.  Also, would make great housewarming/host(ess) gifts." +
                "<br /><br />I am sure other molecular people will be able to tell you more of what you can do with" +
                " it, and I am sure I will experiment with it in cooking - but the main reason I bought it was to make" +
                " my own creamer and it worked out great.", text3.getText());

        Map<Long, OptimizedText.Describer> reviewId3 = text3.getReviewId();
        assertTrue(reviewId3.containsKey(1L));
        assertEquals(1, reviewId3.size());
        assertEquals(3, reviewId3.get(1L).getPart());

    }

    private Review createReview(long id, String text) {
        Review review1 = new Review();
        review1.setId(id);
        review1.setText(text);
        return review1;
    }
}