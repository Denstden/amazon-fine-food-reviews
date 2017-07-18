package com.axondevgroup.reviews.food.model;

import java.io.Serializable;

/**
 * Class for representing food review.
 *
 * @author Denys Storozhenko
 */
public class Review implements Serializable {
    private Long id;
    private String productId;
    private String userId;
    private String profileName;
    private Long helpfulnessNumerator;
    private Long helpfulnessDenominator;
    private Long score;
    private Long time;
    private String summary;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Long getHelpfulnessNumerator() {
        return helpfulnessNumerator;
    }

    public void setHelpfulnessNumerator(Long helpfulnessNumerator) {
        this.helpfulnessNumerator = helpfulnessNumerator;
    }

    public Long getHelpfulnessDenominator() {
        return helpfulnessDenominator;
    }

    public void setHelpfulnessDenominator(Long helpfulnessDenominator) {
        this.helpfulnessDenominator = helpfulnessDenominator;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
