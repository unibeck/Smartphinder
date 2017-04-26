package com.unibeck.controller;

import com.unibeck.SeedDatabase;
import com.unibeck.controllers.SmartphoneController;
import com.unibeck.model.*;
import com.unibeck.repository.CustomerRepository;
import com.unibeck.repository.InventoryRepository;
import com.unibeck.repository.LocationRepository;
import com.unibeck.repository.SmartphoneRepository;
import com.unibeck.services.SmartphoneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jbeckman on 6/17/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SmartphoneControllerTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SmartphoneRepository smartphoneRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private SmartphoneService smartphoneService;

    private SmartphoneController smartphoneController;
    private SeedDatabase seed;

    @Before
    public void setItUp() {
        seed = new SeedDatabase(customerRepository, smartphoneRepository, inventoryRepository, locationRepository);

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

    @Test
    public void findSmartphonesWithConstraintSatisfaction() {
        seed.seedTables();
        Location userLocation = locationRepository.findAll().stream().findAny().get();

        UserConstraint constraint = new UserConstraint(
                Brand.GOOGLE, OS.ANDROID, 799, 3450, 12, 4.0, 128, 534, 5.5
        );

        ResponseEntity<List<Inventory>> result = smartphoneController.findSmartphonesRelatedTo(
                constraint, userLocation.getCity(), userLocation.getState());
        List<Inventory> remainder = result.getBody();
        assertEquals(2, remainder.size());
    }

    @Test
    public void constraintSatisfactionWithBackTracking() {
        seed.seedTables();
        Location userLocation = locationRepository.findAll().stream().findAny().get();

        UserConstraint constraint = new UserConstraint(
                Brand.APPLE, OS.iOS, 799, 3450, 12, 4.0, 128, 534, 5.5
        );

        ResponseEntity<List<Inventory>> result = smartphoneController.findSmartphonesRelatedTo(
                constraint, userLocation.getCity(), userLocation.getState());
        List<Inventory> remainder = result.getBody();
        assertEquals(2, remainder.size());
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
