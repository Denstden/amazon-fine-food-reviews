package com.axondevgroup.reviews.food.httpclient;

import com.axondevgroup.reviews.food.dto.GoogleTranslateDto;

import java.io.Serializable;

public interface ApiTranslator extends Serializable {
    GoogleTranslateDto translate(String text);
}
