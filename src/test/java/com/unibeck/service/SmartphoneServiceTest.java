package com.unibeck.service;

import com.unibeck.SeedDatabase;
import com.unibeck.model.*;
import com.unibeck.repository.SmartphoneRepository;
import com.unibeck.services.SmartphoneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jbeckman on 11/19/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SmartphoneServiceTest {

    @Autowired
    private SmartphoneRepository smartphoneRepository;

    private SmartphoneService smartphoneService;

    @Before
    public void setItUp() {
        smartphoneRepository.deleteAll();

        smartphoneService = new SmartphoneService(smartphoneRepository);
    }

    @Test
    public void createHappyPathEvent() {
        List<Smartphone> smartphoneList = smartphoneService.getAllSmartphones();

        assertEquals(0, smartphoneList.size());
    }

    @Test
    public void basicConstraintWithAndroid() throws Exception {
        SeedDatabase seed = new SeedDatabase(smartphoneRepository);
        seed.seedSmartphones();

        UserConstraint constraint = new UserConstraint(
                Brand.SAMSUNG, OS.ANDROID, 799, 3450, 12, 4.0, 128, 534, 5.5
        );

        LocalDateTime before = LocalDateTime.now();
        List<Smartphone> remainder = smartphoneService.findClosestMatching(constraint);
        LocalDateTime after = LocalDateTime.now();
        System.out.printf("The algorithm took %d ms\n", ChronoUnit.MILLIS.between(before, after));

        assertEquals(2, remainder.size());
    }

    @Test
    public void basicConstraintWithiOS() throws Exception {
        SeedDatabase seed = new SeedDatabase(smartphoneRepository);
        seed.seedSmartphones();

        UserConstraint constraint = new UserConstraint(
                Brand.APPLE, OS.iOS, 799, 3450, 12, 4.0, 128, 534, 5.5
        );

        LocalDateTime before = LocalDateTime.now();
        List<Smartphone> remainder = smartphoneService.findClosestMatching(constraint);
        LocalDateTime after = LocalDateTime.now();
        System.out.printf("The algorithm took %d ms\n", ChronoUnit.MILLIS.between(before, after));
        
        assertEquals(1, remainder.size());
    }
}