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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

        ConstraintSatisfactionResult csr = smartphoneService.findClosestMatching(constraint);
        assertEquals(8, csr.getRemainder().size());

        boolean[] constraintsUsed = csr.getConstraintsUsed();
        assertTrue(constraintsUsed[0]);
        assertTrue(constraintsUsed[1]);
        assertTrue(constraintsUsed[2]);
        assertTrue(constraintsUsed[3]);
        assertTrue(constraintsUsed[4]);
        assertTrue(constraintsUsed[5]);
    }

    @Test
    public void constraintWithBacktrackingWorks() throws Exception {
        SeedDatabase seed = new SeedDatabase(smartphoneRepository);
        seed.seedSmartphones();

        UserConstraint constraint = new UserConstraint(
                Brand.APPLE, OS.iOS, 799, 3450, 12, 4.0, 128, 534, 5.5
        );

        ConstraintSatisfactionResult csr = smartphoneService.findClosestMatching(constraint);
        assertEquals(1, csr.getRemainder().size());

        boolean[] constraintsUsed = csr.getConstraintsUsed();
        assertTrue(constraintsUsed[0]);
        assertTrue(constraintsUsed[1]);
        assertTrue(constraintsUsed[2]);
        assertTrue(constraintsUsed[3]);
        assertFalse(constraintsUsed[4]); // Backtracking here
        assertFalse(constraintsUsed[5]); // And here
    }
}