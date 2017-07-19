package com.axondevgroup.reviews.food.httpclient;

import com.axondevgroup.reviews.food.dto.GoogleTranslateDto;
import com.axondevgroup.reviews.food.dto.GoogleTranslateInputDto;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

/**
 * Class for text translation via api.google.com
 *
 * @author Denys Storozhenko
 */
public class RealGoogleApiTranslator implements ApiTranslator {
    private static final String GOOGLE_API_URL = "https://api.google.com";

    @Override
    public GoogleTranslateDto translate(String text) {
        FeignGoogleApiClient client = Feign
                .builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(FeignGoogleApiClient.class, GOOGLE_API_URL);

        GoogleTranslateInputDto inputDto = new GoogleTranslateInputDto();
        inputDto.setInputLang("en");
        inputDto.setOutputLang("fr");
        inputDto.setText(text);
        return client.translate(inputDto);
    }
}
