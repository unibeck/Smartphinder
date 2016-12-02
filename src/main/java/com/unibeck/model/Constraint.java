package com.unibeck.model;

/**
 * Created by jbeckman on 12/1/16.
 */
public class Constraint {

    private ConstraintType constraintType;
    private NormalizedValue value;

    public Constraint() { }

    public Constraint(ConstraintType constraintType, NormalizedValue value) {
        this.constraintType = constraintType;
        this.value = value;
    }

    public ConstraintType getConstraintType() {
        return constraintType;
    }

    public NormalizedValue getValue() {
        return value;
    }

    public void setValue(NormalizedValue value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Constraint that = (Constraint) o;

        if (constraintType != that.constraintType) return false;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        int result = constraintType != null ? constraintType.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
