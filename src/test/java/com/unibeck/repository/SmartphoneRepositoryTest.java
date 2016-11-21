package com.unibeck.repository;

import com.unibeck.model.Smartphone;

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
}
