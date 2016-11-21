package com.unibeck;

import com.unibeck.model.*;
import com.unibeck.repository.SmartphoneRepository;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;

import static com.unibeck.model.Percentiles.convertFromWithDouble;
import static com.unibeck.model.Percentiles.convertFromWithInt;

/**
 * Created by jbeckman on 11/20/16.
 */
@Component
public class SeedDatabase {
    @Autowired
    private SmartphoneRepository smartphoneRepository;

    private Smartphone newPhone;

    @PostConstruct
    public void seedSmartphones() throws Exception {
        Percentiles per = new Percentiles();

        JSONParser parser = new JSONParser();
        JSONArray arr = (JSONArray) parser.parse(new FileReader("src/main/resources/smartphone.json"));

        for (Object obj : arr) {
            JSONObject p = (JSONObject) obj;

            try {
                newPhone = new Smartphone()
                        .withName((String) p.get("device-name"))
                        .withBrand(Brand.findByAbbr((String) p.get("brand")))
                        .withOperatingSystem(OS.findByAbbr((String) p.get("os")))
                        .withPrice(convertFromWithInt(Integer.valueOf((String) p.get("price")),
                                per.getPricePercentile()))
                        .withDisplaySize(convertFromWithDouble(Double.valueOf((String) p.get("size")),
                                per.getDisplaySizePercentile()))
                        .withDisplayResolution(convertFromWithInt(Integer.valueOf((String) p.get("resolution")),
                                per.getDisplayResolutionPercentile()))
                        .withRam(convertFromWithDouble(Double.valueOf((String) p.get("ram")),
                                per.getRamPercentile()))
                        .withStorage(convertFromWithInt(Integer.valueOf((String) p.get("storage")),
                                per.getStoragePercentile()))
                        .withBatterySize(convertFromWithInt(Integer.valueOf((String) p.get("battery")),
                                per.getBatterySizePercentile()))
                        .withBackCameraSensor(convertFromWithInt(Integer.valueOf((String) p.get("primary")),
                                per.getBackCameraSensorPercentile()));

            } catch (Exception e){
                System.out.println(p.toJSONString());
                throw e;
            }

            smartphoneRepository.save(newPhone);
        }
    }
}