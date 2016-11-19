package com.unibeck.service;

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
    public void createHappyPathEvent() throws IOException {
        List<Smartphone> smartphoneList = smartphoneService.getAllSmartphones();

        assertEquals(0, smartphoneList.size());
    }
}
