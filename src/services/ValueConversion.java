package services;

import model.Smartphone;

import java.util.Collections;
import java.util.List;

/**
 * Created by jbeckman on 11/16/16.
 */
public class ValueConversion {
    private List<Integer> values;
    private int firstFifth;
    private int secondFifth;
    private int thirdFifth;
    private int fourthFifth;
    private int lastFifth;

    private int[] valuePercentile;

    public ValueConversion(List<Integer> values) {
        this.values = values;

        //Casting to int will take the floor
        this.firstFifth  = (int) (values.size() * 0.2);
        this.secondFifth = (int) (values.size() * 0.4);
        this.thirdFifth  = (int) (values.size() * 0.6);
        this.fourthFifth = (int) (values.size() * 0.8);
        this.lastFifth   = values.size();

        setValuePercentile();
    }

    public void setValuePercentile() {
        Collections.sort(values);

        valuePercentile = new int[5];

        valuePercentile[0] = values.get(firstFifth);
        valuePercentile[1] = values.get(secondFifth);
        valuePercentile[2] = values.get(thirdFifth);
        valuePercentile[3] = values.get(fourthFifth);
        valuePercentile[4] = values.get(lastFifth);
    }

    public int[] getValuePercentile() {
        return valuePercentile;
    }
}
