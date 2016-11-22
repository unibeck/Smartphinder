package com.unibeck.service;

import com.unibeck.SeedDatabase;
import com.unibeck.model.Brand;
import com.unibeck.model.OS;
import com.unibeck.model.Smartphone;
import com.unibeck.model.UserConstraint;
import com.unibeck.repository.SmartphoneRepository;
import com.unibeck.services.SmartphoneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Iterator;
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
    public void basicConstraint() throws Exception {
        SeedDatabase seed = new SeedDatabase(smartphoneRepository);
        seed.seedSmartphones();

        UserConstraint constraint = new UserConstraint(
                Brand.APPLE, OS.iOS, 799, 3450, 12, 4.0, 128, 534, 5.5
        );

        //Constraint 1: 40 results left
        //Constraint 2: 40 results left
        //Constraint 3: 22 results left
        List<Smartphone> smartphoneList = smartphoneService.findClosestMatching(constraint);
        assertEquals(22, smartphoneList.size());
    }
}