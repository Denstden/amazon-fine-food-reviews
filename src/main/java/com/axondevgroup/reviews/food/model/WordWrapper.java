package com.axondevgroup.reviews.food.model;

import java.io.Serializable;

/**
 * Class for wrapping String(need for creating dataFrame from sqlContext).
 *
 * @author Denys Storozhenko
 */
public class WordWrapper implements Serializable {
    private String value;

    public WordWrapper(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
