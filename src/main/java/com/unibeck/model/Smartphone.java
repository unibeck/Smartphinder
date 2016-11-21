package com.unibeck.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jbeckman on 11/16/16.
 */
@Entity
public class Smartphone {

    @JsonInclude(value= JsonInclude.Include.NON_NULL)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    protected Long id;

    @NotNull
    @JsonProperty("device-name")
    private String name;

    @NotNull
    @JsonProperty("brand")
    @Enumerated(EnumType.STRING)
    private Brand brand;

    @NotNull
    @JsonProperty("os")
    @Enumerated(EnumType.STRING)
    private OS operatingSystem;

    @NotNull
    @JsonProperty("price")
    private NormalizedValue price;

    @NotNull
    @JsonProperty("size")
    private NormalizedValue displaySize;

    @NotNull
    @JsonProperty("resolution")
    private NormalizedValue displayResolution;

    @NotNull
    @JsonProperty("ram")
    private NormalizedValue ram;

    @NotNull
    @JsonProperty("storage")
    private NormalizedValue storage;

    @NotNull
    @JsonProperty("battery")
    private NormalizedValue batterySize;

    @NotNull
    @JsonProperty("primary")
    private NormalizedValue backCameraSensor;

    public Smartphone() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Brand getBrand() {
        return brand;
    }

    public OS getOperatingSystem() {
        return operatingSystem;
    }

    public NormalizedValue getPrice() {
        return price;
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

    public NormalizedValue getBackCameraSensor() {
        return backCameraSensor;
    }

    public Smartphone withName(String name) {
        this.name = name;
        return this;
    }

    public Smartphone withBrand(Brand brand) {
        this.brand = brand;
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

    public Smartphone withBackCameraSensor(NormalizedValue backCameraSensor) {
        this.backCameraSensor = backCameraSensor;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Smartphone that = (Smartphone) o;

        if (!name.equals(that.name)) return false;
        if (brand != that.brand) return false;
        if (operatingSystem != that.operatingSystem) return false;
        if (price != that.price) return false;
        if (displaySize != that.displaySize) return false;
        if (displayResolution != that.displayResolution) return false;
        if (ram != that.ram) return false;
        if (storage != that.storage) return false;
        if (batterySize != that.batterySize) return false;
        return backCameraSensor == that.backCameraSensor;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (operatingSystem != null ? operatingSystem.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (displaySize != null ? displaySize.hashCode() : 0);
        result = 31 * result + (displayResolution != null ? displayResolution.hashCode() : 0);
        result = 31 * result + (ram != null ? ram.hashCode() : 0);
        result = 31 * result + (storage != null ? storage.hashCode() : 0);
        result = 31 * result + (batterySize != null ? batterySize.hashCode() : 0);
        result = 31 * result + (backCameraSensor != null ? backCameraSensor.hashCode() : 0);
        return result;
    }
}
