package model;

/**
 * Created by jbeckman on 11/16/16.
 */
public enum NormalizedValue {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private int value;

    NormalizedValue(int val) {
        this.value = val;
    }
}
