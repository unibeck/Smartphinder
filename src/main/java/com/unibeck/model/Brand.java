package com.unibeck.model;

/**
 * Created by jbeckman on 11/19/16.
 */
public enum Brand {

    APPLE("Apple"),
    GOOGLE("Google"),
    HTC("HTC"),
    SAMSUNG("Samsung"),
    LG("LG"),
    NOKIA("Nokia"),
    MICROSOFT("Microsoft"),
    SONY("Sony");

    private String value;

    Brand(String val) {
        this.value = val;
    }
}
