package com.unibeck.model;

import java.lang.reflect.Field;

/**
 * Created by jbeckman on 11/20/16.
 */
public class Constraint {

    private Field field;
    private NormalizedValue maxValue; //Inclusive

    public Constraint(Field field, NormalizedValue maxValue) {
        this.field = field;
        this.maxValue = maxValue;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public NormalizedValue getValue() {
        return maxValue;
    }

    public void setValue(NormalizedValue maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Constraint that = (Constraint) o;

        if (field != null ? !field.equals(that.field) : that.field != null) return false;
        return maxValue == that.maxValue;
    }

    @Override
    public int hashCode() {
        int result = field != null ? field.hashCode() : 0;
        result = 31 * result + (maxValue != null ? maxValue.hashCode() : 0);
        return result;
    }
}
