package com.axondevgroup.reviews.food.dto;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Class for representation request to google api.
 *
 * @author Denys Storozhenko
 */
public class GoogleTranslateInputDto implements Serializable {
    @JsonProperty("input_lang")
    private String inputLang;
    @JsonProperty("output_lang")
    private String outputLang;
    private String text;

    public String getInputLang() {
        return inputLang;
    }

    public void setInputLang(String inputLang) {
        this.inputLang = inputLang;
    }

    public String getOutputLang() {
        return outputLang;
    }

    public void setOutputLang(String outputLang) {
        this.outputLang = outputLang;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
