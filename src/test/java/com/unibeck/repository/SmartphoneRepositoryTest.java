package com.unibeck.repository;

import com.unibeck.model.Brand;
import com.unibeck.model.NormalizedValue;
import com.unibeck.model.OS;
import com.unibeck.model.Smartphone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.unibeck.model.Percentiles.convertFromWithDouble;
import static com.unibeck.model.Percentiles.convertFromWithInt;
import static org.junit.Assert.assertEquals;

/**
 * Created by jbeckman on 11/19/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartphoneRepositoryTest {

    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @Before
    public void setup() {
        smartphoneRepository.deleteAll();
    }

    @Test
    public void emptySmartphoneRepo() {
        List<Smartphone> smartphoneList = smartphoneRepository.findAll();
        assertEquals(0, smartphoneList.size());
    }

    @Test
    public void nonEmptySmartphoneRepo() {
        List<Smartphone> smartphoneList = smartphoneRepository.findAll();
        assertEquals(0, smartphoneList.size());

        smartphoneRepository.save(new Smartphone()
                .withName("iPhone 4")
                .withBrand(Brand.APPLE)
                .withOperatingSystem(OS.iOS)
                .withPrice(NormalizedValue.ONE)
                .withDisplaySize(NormalizedValue.ONE)
                .withDisplayResolution(NormalizedValue.ONE)
                .withRam(NormalizedValue.ONE)
                .withStorage(NormalizedValue.ONE)
                .withBattery(NormalizedValue.ONE)
                .withCamera(NormalizedValue.ONE)
        );

        smartphoneList = smartphoneRepository.findAll();
        assertEquals(1, smartphoneList.size());
    }
}
