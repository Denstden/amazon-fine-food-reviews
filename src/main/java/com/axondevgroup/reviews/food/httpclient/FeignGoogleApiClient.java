package com.axondevgroup.reviews.food.httpclient;

import com.axondevgroup.reviews.food.dto.GoogleTranslateDto;
import com.axondevgroup.reviews.food.dto.GoogleTranslateInputDto;
import feign.Headers;
import feign.RequestLine;

import java.io.Serializable;

@Headers("Content-Type: application/json")
public interface FeignGoogleApiClient extends Serializable {

    @RequestLine("POST /translate")
    GoogleTranslateDto translate(GoogleTranslateInputDto inputDto);
}
