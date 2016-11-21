package com.unibeck.services;

import com.unibeck.model.Brand;
import com.unibeck.model.NormalizedValue;
import com.unibeck.model.OS;
import com.unibeck.model.Smartphone;
import com.unibeck.repository.SmartphoneRepository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;

/**
 * Created by jbeckman on 11/20/16.
 */
@Component
public class SeedDatabase {
    @Autowired
    private SmartphoneRepository smartphoneRepository;

    private Smartphone newPhone;
    int[] pricePercentile = {399, 599, 799, 899, 1099};
    double[] displaySizePercentile = {4.0, 4.5, 5.0, 5.5, 6.0};
    int[] displayResolutionPercentile = {250, 300, 350, 400, 450};
    double[] ramPercentile = {1.0, 2.0, 3.0, 4.0, 5.0};
    int[] storagePercentile = {16, 32, 64, 128, 256};
    int[] batterySizePercentile = {1500, 2250, 3000, 3750, 4500};
    int[] backCameraSensorPercentile = {5, 8, 10, 12, 16};

    @PostConstruct
    public void seedSmartphones() throws Exception {
        JSONParser parser = new JSONParser();
        JSONArray arr = (JSONArray) parser.parse(new FileReader("src/main/resources/smartphone.json"));

        for (Object obj : arr) {
            JSONObject p = (JSONObject) obj;

            newPhone = new Smartphone()
                    .withName((String) p.get("device-name"))
                    .withBrand(Brand.findByAbbr((String) p.get("brand")))
                    .withOperatingSystem(OS.findByAbbr((String) p.get("os")))
                    .withPrice(convertFromWithInt(Integer.valueOf((String) p.get("price")), pricePercentile))
                    .withDisplaySize(convertFromWithDouble(Double.valueOf((String) p.get("size")), displaySizePercentile))
                    .withDisplayResolution(convertFromWithInt(Integer.valueOf((String) p.get("resolution")), displayResolutionPercentile))
                    .withRam(convertFromWithDouble(Double.valueOf((String) p.get("ram")), ramPercentile))
                    .withStorage(convertFromWithInt(Integer.valueOf((String) p.get("storage")), storagePercentile))
                    .withBatterySize(convertFromWithInt(Integer.valueOf((String) p.get("battery")), batterySizePercentile))
                    .withBackCameraSensor(convertFromWithInt(Integer.valueOf((String) p.get("primary")), backCameraSensorPercentile));

            smartphoneRepository.save(newPhone);
        }
    }

    private static NormalizedValue convertFromWithInt(int value, int[] valuePercentile) {
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

    private static NormalizedValue convertFromWithDouble(double value, double[] valuePercentile) {
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
}