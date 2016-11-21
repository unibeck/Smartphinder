package com.unibeck.model;

/**
 * Created by jbeckman on 11/19/16.
 */
public enum Brand {
    APPLE("Apple"),
    GOOGLE("Google"),
    HTC("HTC"),
    HUAWEI("Huawei"),
    SAMSUNG("Samsung"),
    LG("LG"),
    NOKIA("Nokia"),
    MICROSOFT("Microsoft"),
    SONY("Sony");

    private String value;

    Brand(String val) {
        this.value = val;
    }

    public static Brand findByAbbr(String abbr) {
        for (Brand b : values()) {
            if (b.value.equals(abbr)) {
                return b;
            }
        }

        throw new IllegalArgumentException(abbr);
    }
}
