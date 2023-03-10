package com.unibeck.model;

/**
 * Created by jbeckman on 11/21/16.
 */
public class Percentiles {

    private int[] pricePercentile = new int[5];
    private double[] displaySizePercentile = new double[5];
    private int[] displayResolutionPercentile = new int[5];
    private double[] ramPercentile = new double[5];
    private int[] storagePercentile = new int[5];
    private int[] batteryPercentile = new int[5];
    private int[] cameraPercentile = new int[5];

    public Percentiles() {
        pricePercentile = new int[]{79, 109, 249, 549, 699};
        displaySizePercentile = new double[]{4.0, 4.5, 5.0, 5.5, 6.0};
        displayResolutionPercentile = new int[]{250, 300, 350, 400, 450};
        ramPercentile = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
        storagePercentile = new int[]{16, 32, 64, 128, 256};
        batteryPercentile = new int[]{1500, 2250, 3000, 3750, 4500};
        cameraPercentile = new int[]{5, 8, 10, 12, 16};
    }

    public static NormalizedValue convertFromWithInt(int value, int[] valuePercentile) {
        if(value <= valuePercentile[0]) {
            return NormalizedValue.ONE;
        } else if (value <= valuePercentile[1]) {
            return NormalizedValue.TWO;

        } else if (value <= valuePercentile[2]) {
            return NormalizedValue.THREE;

        } else if (value <= valuePercentile[3]) {
            return NormalizedValue.FOUR;
        } else {
            return NormalizedValue.FIVE;
        }
    }

    public static NormalizedValue convertFromWithDouble(double value, double[] valuePercentile) {
        if(value <= valuePercentile[0]) {
            return NormalizedValue.ONE;
        } else if (value <= valuePercentile[1]) {
            return NormalizedValue.TWO;

        } else if (value <= valuePercentile[2]) {
            return NormalizedValue.THREE;

        } else if (value <= valuePercentile[3]) {
            return NormalizedValue.FOUR;
        } else {
            return NormalizedValue.FIVE;
        }
    }

    public int[] getPricePercentile() {
        return pricePercentile;
    }

    public double[] getDisplaySizePercentile() {
        return displaySizePercentile;
    }

    public int[] getDisplayResolutionPercentile() {
        return displayResolutionPercentile;
    }

    public double[] getRamPercentile() {
        return ramPercentile;
    }

    public int[] getStoragePercentile() {
        return storagePercentile;
    }

    public int[] getBatteryPercentile() {
        return batteryPercentile;
    }

    public int[] getCameraPercentile() {
        return cameraPercentile;
    }
}
