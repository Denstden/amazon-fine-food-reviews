package com.axondevgroup.reviews.food.exception;

import java.io.Serializable;
import java.text.MessageFormat;

public class InvalidCommandLineArgumentException extends Exception implements Serializable {

    public InvalidCommandLineArgumentException(String message) {
        super(MessageFormat.format("Invalid command line argument name '{0}'. Accepted values: [file, translate]", message));
    }

}
