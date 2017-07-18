package com.axondevgroup.reviews.food.exception;

import java.text.MessageFormat;

public class InvalidCommandLineArgumentException extends Exception {

    public InvalidCommandLineArgumentException(String message) {
        super(MessageFormat.format("Invalid command line argument name '{0}'. Accepted values: [file, translate]", message));
    }

}
