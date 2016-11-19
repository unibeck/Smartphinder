package com.unibeck.controller;


import com.unibeck.controllers.SmartphoneController;
import com.unibeck.model.NormalizedValue;
import com.unibeck.model.OS;
import com.unibeck.model.Smartphone;
import com.unibeck.repository.SmartphoneRepository;
import com.unibeck.services.SmartphoneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
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

    private Smartphone smartphone;
    int[] pricePercentile = {399, 599, 799, 899, 1099};
    double[] displaySizePercentile = {4.0, 4.5, 5.0, 5.5, 6.0};
    int[] displayResolutionPercentile = {250, 300, 350, 400, 450};
    double[] ramPercentile = {1.0, 2.0, 3.0, 4.0, 5.0};
    int[] storagePercentile = {16, 32, 64, 128, 256};
    int[] batterySizePercentile = {1500, 2250, 3000, 3750, 4500};
    int[] batteryEndurancePercentile = {55, 60, 65, 70, 75};
    int[] backCameraSensorPercentile = {5, 8, 10, 12, 16};
    double[] frontCameraSensorPercentile = {1.3, 2.1, 5.0, 8.0, 8.7};

    @Before
    public void setItUp() {
        smartphoneRepository.deleteAll();

        smartphoneService = new SmartphoneService(smartphoneRepository);
        smartphoneController = new SmartphoneController(smartphoneService);
    }

    @Test
    public void getHappyPathEvents() throws IOException {
        List<Smartphone> smartphones = smartphoneController.getAllSmartphones();
        assertEquals(0, smartphones.size());

        Smartphone iPhone4 = new Smartphone()
                .withName("iPhone 4")
                .withOperatingSystem(OS.APPLE)
                .withPrice(convertFromWithInt(40, pricePercentile))
                .withDisplaySize(convertFromWithDouble(3.5, displaySizePercentile))
                .withDisplayResolution(convertFromWithInt(163, displayResolutionPercentile))
                .withRam(convertFromWithDouble(0.256, ramPercentile))
                .withStorage(convertFromWithInt(16, storagePercentile))
                .withBatterySize(convertFromWithInt(1420, batterySizePercentile))
                .withBackCameraSensor(convertFromWithInt(5, backCameraSensorPercentile))
                .withFrontCameraSensor(convertFromWithDouble(1.3, frontCameraSensorPercentile));

//        OkHttpClient client = new OkHttpClient();
//
//        String run(String url) throws IOException {
//            Request request = new Request.Builder()
//                    .url(url)
//                    .build();
//
//            Response response = client.newCall(request).execute();
//            return response.body().string();
//        }

        smartphoneRepository.save(iPhone4);

        smartphones = smartphoneController.getAllSmartphones();
        assertEquals(1, smartphones.size());
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
}
