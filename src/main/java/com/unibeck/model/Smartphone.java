package com.unibeck.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jbeckman on 11/16/16.
 */
@Entity
public class Smartphone {

    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    @JsonProperty("name")
    private String name;
    private OS operatingSystem;
    private NormalizedValue price;
    private NormalizedValue height;
    private NormalizedValue width;

    private NormalizedValue depth;

    @JsonProperty("display-size")
    private NormalizedValue displaySize;

    private NormalizedValue displayResolution;
    private NormalizedValue ram;
    private NormalizedValue storage;
    private NormalizedValue batterySize;
    private NormalizedValue batteryEndurance;
    private NormalizedValue backCameraSensor;
    private NormalizedValue frontCameraSensor;

    public Smartphone() {}

    public String getName() {
        return name;
    }

    public OS getOperatingSystem() {
        return operatingSystem;
    }

    public NormalizedValue getPrice() {
        return price;
    }

    public NormalizedValue getHeight() {
        return height;
    }

    public NormalizedValue getWidth() {
        return width;
    }

    public NormalizedValue getDepth() {
        return depth;
    }

    public NormalizedValue getDisplaySize() {
        return displaySize;
    }

    public NormalizedValue getDisplayResolution() {
        return displayResolution;
    }

    public NormalizedValue getRam() {
        return ram;
    }

    public NormalizedValue getStorage() {
        return storage;
    }

    public NormalizedValue getBatterySize() {
        return batterySize;
    }

    public NormalizedValue getBatteryEndurance() {
        return batteryEndurance;
    }

    public NormalizedValue getBackCameraSensor() {
        return backCameraSensor;
    }

    public NormalizedValue getFrontCameraSensor() {
        return frontCameraSensor;
    }

    public Smartphone withName(String name) {
        this.name = name;
        return this;
    }

    public Smartphone withOperatingSystem(OS operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public Smartphone withPrice(NormalizedValue price) {
        this.price = price;
        return this;
    }

    public Smartphone withHeight(NormalizedValue height) {
        this.height = height;
        return this;
    }

    public Smartphone withWidth(NormalizedValue width) {
        this.width = width;
        return this;
    }

    public Smartphone withDepth(NormalizedValue depth) {
        this.depth = depth;
        return this;
    }

    public Smartphone withDisplaySize(NormalizedValue displaySize) {
        this.displaySize = displaySize;
        return this;
    }

    public Smartphone withDisplayResolution(NormalizedValue displayResolution) {
        this.displayResolution = displayResolution;
        return this;
    }

    public Smartphone withRam(NormalizedValue ram) {
        this.ram = ram;
        return this;
    }

    public Smartphone withStorage(NormalizedValue storage) {
        this.storage = storage;
        return this;
    }

    public Smartphone withBatterySize(NormalizedValue batterySize) {
        this.batterySize = batterySize;
        return this;
    }

    public Smartphone withBatteryEndurance(NormalizedValue batteryEndurance) {
        this.batteryEndurance = batteryEndurance;
        return this;
    }

    public Smartphone withBackCameraSensor(NormalizedValue backCameraSensor) {
        this.backCameraSensor = backCameraSensor;
        return this;
    }

    public Smartphone withFrontCameraSensor(NormalizedValue frontCameraSensor) {
        this.frontCameraSensor = frontCameraSensor;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Smartphone that = (Smartphone) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (operatingSystem != that.operatingSystem) return false;
        if (price != that.price) return false;
        if (height != that.height) return false;
        if (width != that.width) return false;
        if (depth != that.depth) return false;
        if (displaySize != that.displaySize) return false;
        if (displayResolution != that.displayResolution) return false;
        if (ram != that.ram) return false;
        if (storage != that.storage) return false;
        if (batterySize != that.batterySize) return false;
        if (batteryEndurance != that.batteryEndurance) return false;
        if (backCameraSensor != that.backCameraSensor) return false;
        return frontCameraSensor == that.frontCameraSensor;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (operatingSystem != null ? operatingSystem.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (width != null ? width.hashCode() : 0);
        result = 31 * result + (depth != null ? depth.hashCode() : 0);
        result = 31 * result + (displaySize != null ? displaySize.hashCode() : 0);
        result = 31 * result + (displayResolution != null ? displayResolution.hashCode() : 0);
        result = 31 * result + (ram != null ? ram.hashCode() : 0);
        result = 31 * result + (storage != null ? storage.hashCode() : 0);
        result = 31 * result + (batterySize != null ? batterySize.hashCode() : 0);
        result = 31 * result + (batteryEndurance != null ? batteryEndurance.hashCode() : 0);
        result = 31 * result + (backCameraSensor != null ? backCameraSensor.hashCode() : 0);
        result = 31 * result + (frontCameraSensor != null ? frontCameraSensor.hashCode() : 0);
        return result;
    }
}
