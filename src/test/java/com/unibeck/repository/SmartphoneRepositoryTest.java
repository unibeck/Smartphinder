package com.unibeck.repository;

import com.unibeck.SeedDatabase;
import com.unibeck.model.*;

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
@Transactional
public class SmartphoneRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SmartphoneRepository smartphoneRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private LocationRepository locationRepository;

    private SeedDatabase seed;

    @Before
    public void setup() {
        seed = new SeedDatabase(customerRepository, smartphoneRepository, inventoryRepository, locationRepository);
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

    @Test
    public void largerSmartphoneRepo() {
        seed.seedTables();

        assertEquals(61, smartphoneRepository.count());
    }

    @Test
    public void filteringBySmartphoneFieldWorks() {
        seed.seedTables();

        //We can use this test to hone in on the percentile, one fifth of the repository should be the size of each percentile
        //Thus this test should result in 17 smartphones from the first and second percentile
        List<Smartphone> smartphoneList = smartphoneRepository.findByPriceLessThan(NormalizedValue.THREE);
        assertEquals(20, smartphoneList.size());
    }
}

// Alternatively, we can use this to determine the percentiles also
//    long count = smartphoneRepository.count();
//        for (int i = 0; i < 5; i++) {
//        System.out.printf("The %dth percentile ends with ", i);
//        Page<Smartphone> sp = smartphoneRepository.findAll(new PageRequest(i, (int) count/5, Sort.Direction.ASC, "price"));
//
//
//        Iterator<Smartphone> itr = sp.iterator();
//        Smartphone lastElement = itr.next();
//        while(itr.hasNext()) {
//        lastElement=itr.next();
//        }
//        System.out.printf("%s\n", lastElement.getPrice().toString());
//        }
