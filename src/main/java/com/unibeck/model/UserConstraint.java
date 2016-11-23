package com.unibeck.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by jbeckman on 11/21/16.
 */
public class UserConstraint {
    @NotNull
    @JsonProperty("brand")
    private Brand brand;

    @NotNull
    @JsonProperty("os")
    private OS operatingSystem;

    @NotNull
    @JsonProperty("price")
    private int price;

    @NotNull
    @JsonProperty("battery")
    private int battery;

    @NotNull
    @JsonProperty("camera")
    private int camera;

    @NotNull
    @JsonProperty("ram")
    private double ram;

    @NotNull
    @JsonProperty("storage")
    private int storage;

    @NotNull
    @JsonProperty("resolution")
    private int resolution;

    @NotNull
    @JsonProperty("display-size")
    private double displaySize;

    public UserConstraint() {}

    public UserConstraint(Brand brand, OS operatingSystem, int price, int battery, int camera, double ram, int storage, int resolution, double displaySize) {
        this.brand = brand;
        this.operatingSystem = operatingSystem;
        this.price = price;
        this.battery = battery;
        this.camera = camera;
        this.ram = ram;
        this.storage = storage;
        this.resolution = resolution;
        this.displaySize = displaySize;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public OS getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OS operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getCamera() {
        return camera;
    }

    public void setCamera(int camera) {
        this.camera = camera;
    }

    public double getRam() {
        return ram;
    }

    public void setRam(double ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public double getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(double displaySize) {
        this.displaySize = displaySize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserConstraint that = (UserConstraint) o;

        if (price != that.price) return false;
        if (battery != that.battery) return false;
        if (camera != that.camera) return false;
        if (Double.compare(that.ram, ram) != 0) return false;
        if (storage != that.storage) return false;
        if (resolution != that.resolution) return false;
        if (Double.compare(that.displaySize, displaySize) != 0) return false;
        if (brand != that.brand) return false;
        return operatingSystem == that.operatingSystem;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = brand != null ? brand.hashCode() : 0;
        result = 31 * result + (operatingSystem != null ? operatingSystem.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + battery;
        result = 31 * result + camera;
        temp = Double.doubleToLongBits(ram);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + storage;
        result = 31 * result + resolution;
        temp = Double.doubleToLongBits(displaySize);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
