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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (id != null ? !id.equals(review.id) : review.id != null) return false;
        if (productId != null ? !productId.equals(review.productId) : review.productId != null) return false;
        if (userId != null ? !userId.equals(review.userId) : review.userId != null) return false;
        if (profileName != null ? !profileName.equals(review.profileName) : review.profileName != null) return false;
        if (helpfulnessNumerator != null ? !helpfulnessNumerator.equals(review.helpfulnessNumerator) : review.helpfulnessNumerator != null)
            return false;
        if (helpfulnessDenominator != null ? !helpfulnessDenominator.equals(review.helpfulnessDenominator) : review.helpfulnessDenominator != null)
            return false;
        if (score != null ? !score.equals(review.score) : review.score != null) return false;
        if (time != null ? !time.equals(review.time) : review.time != null) return false;
        if (summary != null ? !summary.equals(review.summary) : review.summary != null) return false;
        return text != null ? text.equals(review.text) : review.text == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (profileName != null ? profileName.hashCode() : 0);
        result = 31 * result + (helpfulnessNumerator != null ? helpfulnessNumerator.hashCode() : 0);
        result = 31 * result + (helpfulnessDenominator != null ? helpfulnessDenominator.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
