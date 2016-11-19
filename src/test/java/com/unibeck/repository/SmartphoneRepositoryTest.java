package com.unibeck.repository;

import com.unibeck.model.Smartphone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by jbeckman on 11/19/2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SmartphoneRepositoryTest {

    @Autowired
    private SmartphoneRepository smartphoneRepository;

    @Before
    public void setup() {
        smartphoneRepository.deleteAll();
    }

    @Test
    public void emptySmartphoneRepo() throws IOException {
        List<Smartphone> smartphoneList = smartphoneRepository.findAll();
        assertTrue(smartphoneList.size() == 0);
    }
}
