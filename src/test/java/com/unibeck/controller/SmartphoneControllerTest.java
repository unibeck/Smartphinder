package com.unibeck.controller;


import com.unibeck.controllers.SmartphoneController;
import com.unibeck.model.*;
import com.unibeck.repository.SmartphoneRepository;
import com.unibeck.services.SmartphoneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jbeckman on 6/17/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartphoneControllerTest {

    @Autowired
    private SmartphoneRepository smartphoneRepository;
    @Autowired
    private SmartphoneService smartphoneService;

    private SmartphoneController smartphoneController;

    @Before
    public void setItUp() {
        smartphoneRepository.deleteAll();

        smartphoneService = new SmartphoneService(smartphoneRepository);
        smartphoneController = new SmartphoneController(smartphoneService);
    }

    @Test
    public void getHappyPathSmartphones() {
        List<Smartphone> smartphones = smartphoneController.getAllSmartphones();
        assertEquals(0, smartphones.size());

        Smartphone iPhone4 = new Smartphone()
                .withName("iPhone 4")
                .withBrand(Brand.APPLE)
                .withOperatingSystem(OS.iOS)
                .withPrice(NormalizedValue.ONE)
                .withDisplaySize(NormalizedValue.ONE)
                .withDisplayResolution(NormalizedValue.ONE)
                .withRam(NormalizedValue.ONE)
                .withStorage(NormalizedValue.ONE)
                .withBattery(NormalizedValue.ONE)
                .withCamera(NormalizedValue.ONE);

        smartphoneRepository.save(iPhone4);

        smartphones = smartphoneController.getAllSmartphones();
        assertEquals(1, smartphones.size());
    }

//     new Smartphone()
//                .withName("iPhone 4")
//                .withBrand(Brand.APPLE)
//                .withOperatingSystem(OS.iOS)
//                .withPrice(convertFromWithInt(40, per.getPricePercentile()))
//            .withDisplaySize(convertFromWithDouble(3.5, per.getDisplaySizePercentile()))
//            .withDisplayResolution(convertFromWithInt(163, per.getDisplayResolutionPercentile()))
//            .withRam(convertFromWithDouble(0.256, per.getRamPercentile()))
//            .withStorage(convertFromWithInt(16, per.getStoragePercentile()))
//            .withBattery(convertFromWithInt(1420, per.getBatteryPercentile()))
//            .withCamera(convertFromWithInt(5, per.getCameraPercentile()));
}
