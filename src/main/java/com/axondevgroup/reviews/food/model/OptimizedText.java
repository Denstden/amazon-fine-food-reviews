package com.axondevgroup.reviews.food.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Class for representing optimized text for Google API(to make less rest calls for translation).
 *
 * @author Denys Storozhenko
 */
public class OptimizedText implements Serializable {
    private Map<Long, Describer> reviewId;
    private String text;

    public Map<Long, Describer> getReviewId() {
        return reviewId;
    }

    public void setReviewId(Map<Long, Describer> reviewId) {
        this.reviewId = reviewId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    /**
     * Class for description part of review's text.
     */
    public static class Describer {
        private int indexFrom;
        private int indexTo;
        private int part;

        public Describer(int indexFrom, int indexTo, int part) {
            this.indexFrom = indexFrom;
            this.indexTo = indexTo;
            this.part = part;
        }

        public int getIndexFrom() {
            return indexFrom;
        }

        public void setIndexFrom(int indexFrom) {
            this.indexFrom = indexFrom;
        }

        public int getIndexTo() {
            return indexTo;
        }

        public void setIndexTo(int indexTo) {
            this.indexTo = indexTo;
        }

        public int getPart() {
            return part;
        }

        public void setPart(int part) {
            this.part = part;
        }

        @Override
        public String toString() {
            return "Describer{" +
                    "indexFrom=" + indexFrom +
                    ", indexTo=" + indexTo +
                    ", part=" + part +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OptimizedText{" +
                "reviewId=" + reviewId +
                ", text='" + text + '\'' +
                '}';
    }
}
