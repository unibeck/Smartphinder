package com.unibeck.service;

import com.unibeck.SeedDatabase;
import com.unibeck.model.Brand;
import com.unibeck.model.OS;
import com.unibeck.model.Smartphone;
import com.unibeck.model.UserConstraint;
import com.unibeck.repository.CustomerRepository;
import com.unibeck.repository.InventoryRepository;
import com.unibeck.repository.LocationRepository;
import com.unibeck.repository.SmartphoneRepository;
import com.unibeck.services.ConstraintSatisfactionAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by jbeckman on 11/19/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ConstraintSatisfactionAlgorithmTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SmartphoneRepository smartphoneRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private ConstraintSatisfactionAlgorithm constraintSatisfactionAlgorithm;

    private SeedDatabase seed;

    @Before
    public void setItUp() {
        seed = new SeedDatabase(customerRepository, smartphoneRepository, inventoryRepository, locationRepository);
    }

    @Test
    public void basicConstraintWithAndroid() {
        seed.seedTables();

        UserConstraint constraint = new UserConstraint(
                Brand.SAMSUNG, OS.ANDROID, 799, 3450, 12, 4.0, 128, 534, 5.5
        );

        List<Smartphone> remainder = constraintSatisfactionAlgorithm.findClosestMatching(
                constraint, smartphoneRepository.findAll());
        assertEquals(2, remainder.size());
    }

    @Test
    public void basicConstraintWithiOS() {
        seed.seedTables();

        UserConstraint constraint = new UserConstraint(
                Brand.APPLE, OS.iOS, 799, 3450, 12, 4.0, 128, 534, 5.5
        );

        List<Smartphone> remainder = constraintSatisfactionAlgorithm.findClosestMatching(
                constraint, smartphoneRepository.findAll());
        assertEquals(2, remainder.size());
    }

    @Test
    public void basicConstraintWithLowEndPhone() {
        seed.seedTables();

        UserConstraint constraint = new UserConstraint(
                Brand.APPLE, OS.iOS, 256, 2000, 8, 2.0, 32, 278, 4.6
        );

        List<Smartphone> remainder = constraintSatisfactionAlgorithm.findClosestMatching(
                constraint, smartphoneRepository.findAll());

        assertEquals(4, remainder.size());
    }
}
