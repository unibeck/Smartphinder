package com.unibeck.model;

/**
 * Created by jbeckman on 11/16/16.
 */
public enum OS {
    ANDROID("Android"),
    iOS("iOS"),
    WINDOWS_TEN_MOBILE("Windows 10 Mobile"),
    WINDOWS_PHONE_EIGHT("Windows Phone 8");

    private String value;

    OS(String val) {
        this.value = val;
    }

    public static OS findByAbbr(String abbr) {
        for (OS os : values()) {
            if (os.value.equals(abbr)) {
                return os;
            }
        }
        return null;
    }
}
