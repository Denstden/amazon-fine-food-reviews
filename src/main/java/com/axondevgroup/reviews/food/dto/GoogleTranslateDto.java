package com.axondevgroup.reviews.food.dto;

import java.io.Serializable;

/**
 * Class for representation response from google api.
 *
 * @author Denys Storozhenko
 */
public class GoogleTranslateDto implements Serializable {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
