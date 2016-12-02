package com.unibeck.model;

/**
 * Created by jbeckman on 11/19/16.
 */
public enum ConstraintType {
    BATTERY("battery"),
    CAMERA("camera"),
    DISPLAY_SIZE("displaySize"),
    HEAD_TAUTOLOGY("headTautology"),
    PRICE("price"),
    RAM("ram"),
    RESOLUTION("resolution"),
    STORAGE("storage");

    private String value;

    ConstraintType(String val) {
        this.value = val;
    }

    public static ConstraintType findByAbbr(String abbr) {
        for (ConstraintType b : values()) {
            if (b.value.equals(abbr)) {
                return b;
            }
        }

        throw new IllegalArgumentException(abbr);
    }
}
