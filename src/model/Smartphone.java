package model;

/**
 * Created by jbeckman on 11/16/16.
 */
public class Smartphone {

    private String name;
    private OS operatingSystem;
    private NormalizedValue price;
    private NormalizedValue height;
    private NormalizedValue width;
    private NormalizedValue depth;
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
}
