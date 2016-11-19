package com.unibeck.model;

/**
 * Created by jbeckman on 11/16/16.
 */
public enum OS {
    ANDROID("Android"),
    APPLE("Apple"),
    WINDOWS_PHONE("Windows Phone");

    private String value;

    OS(String val) {
        this.value = val;
    }
}
