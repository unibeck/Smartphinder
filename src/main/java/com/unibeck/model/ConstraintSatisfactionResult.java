package com.unibeck.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jbeckman on 11/23/16.
 */
public class ConstraintSatisfactionResult {

    private List<Smartphone> remainder;
    private boolean[] constraintsUsed;

    public ConstraintSatisfactionResult() {}

    public ConstraintSatisfactionResult(List<Smartphone> remainder, boolean[] constraintsUsed) {
        this.remainder = remainder;
        this.constraintsUsed = constraintsUsed;
    }

    public List<Smartphone> getRemainder() {
        return remainder;
    }

    public void setRemainder(List<Smartphone> remainder) {
        this.remainder = remainder;
    }

    public boolean[] getConstraintsUsed() {
        return constraintsUsed;
    }

    public void setConstraintsUsed(boolean[] constraintsUsed) {
        this.constraintsUsed = constraintsUsed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstraintSatisfactionResult that = (ConstraintSatisfactionResult) o;

        if (remainder != null ? !remainder.equals(that.remainder) : that.remainder != null) return false;
        return Arrays.equals(constraintsUsed, that.constraintsUsed);
    }

    @Override
    public int hashCode() {
        int result = remainder != null ? remainder.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(constraintsUsed);
        return result;
    }
}
