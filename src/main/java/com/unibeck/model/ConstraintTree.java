package com.unibeck.model;

import java.util.*;

/**
 * Created by jbeckman on 12/1/16.
 */
public class ConstraintTree {
    private List<ConstraintTree> children;
    private Constraint constraint;

    public ConstraintTree(List<ConstraintTree> children, Constraint constraint) {
        this.children = children;
        this.constraint = constraint;
    }

    public ConstraintTree(Constraint constraint) {
        this.constraint = constraint;
    }

    public List<ConstraintTree> getChildren() {
        return children;
    }

    public void setChildren(List<ConstraintTree> children) {
        this.children = children;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }

    public boolean addChild(ConstraintTree tree) {
        return children.add(tree);
    }

    public boolean removeChild(ConstraintTree tree) {
        return children.remove(tree);
    }

    public Iterator<ConstraintTree> iterator() {
        return children.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConstraintTree that = (ConstraintTree) o;

        if (children != null ? !children.equals(that.children) : that.children != null) return false;
        return constraint != null ? constraint.equals(that.constraint) : that.constraint == null;
    }

    @Override
    public int hashCode() {
        int result = children != null ? children.hashCode() : 0;
        result = 31 * result + (constraint != null ? constraint.hashCode() : 0);
        return result;
    }
}
