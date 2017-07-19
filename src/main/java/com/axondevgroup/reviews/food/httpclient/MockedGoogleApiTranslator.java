package com.axondevgroup.reviews.food.httpclient;

import com.axondevgroup.reviews.food.dto.GoogleTranslateDto;

/**
 * Class for text translation via mock
 *
 * @author Denys Storozhenko
 */
public class MockedGoogleApiTranslator implements ApiTranslator {
    @Override
    public GoogleTranslateDto translate(String text) {
        GoogleTranslateDto result = new GoogleTranslateDto();
        result.setText(text);
        return result;
    }
}
