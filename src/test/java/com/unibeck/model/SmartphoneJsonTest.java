package com.unibeck.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jbeckman on 11/19/2016.
 */
public class SmartphoneJsonTest {

    private static final String IPHONE4 =
            "{\"device-name\":\"iPhone 4\",\"brand\":\"APPLE\",\"os\":\"iOS\",\"price\":\"ONE\",\"display-size\":\"ONE\","+
            "\"resolution\":\"ONE\",\"ram\":\"ONE\",\"storage\":\"ONE\",\"battery\":\"TWO\",\"camera\":\"ONE\"}";

    @Test
    public void basicSerializationWorks() throws JsonProcessingException {
        Smartphone iPhone4 = new Smartphone()
                .withName("iPhone 4")
                .withBrand(Brand.APPLE)
                .withOperatingSystem(OS.iOS)
                .withPrice(NormalizedValue.ONE)
                .withDisplaySize(NormalizedValue.ONE)
                .withDisplayResolution(NormalizedValue.ONE)
                .withRam(NormalizedValue.ONE)
                .withStorage(NormalizedValue.ONE)
                .withBattery(NormalizedValue.TWO)
                .withCamera(NormalizedValue.ONE);

        String serialized = new ObjectMapper().writeValueAsString(iPhone4);

        assertEquals(IPHONE4, serialized);
    }

    @Test
    public void basicDeserializationWorks() throws Exception {
        Smartphone iPhone4 = new Smartphone()
                .withName("iPhone 4")
                .withBrand(Brand.APPLE)
                .withOperatingSystem(OS.iOS)
                .withPrice(NormalizedValue.ONE)
                .withDisplaySize(NormalizedValue.ONE)
                .withDisplayResolution(NormalizedValue.ONE)
                .withRam(NormalizedValue.ONE)
                .withStorage(NormalizedValue.ONE)
                .withBattery(NormalizedValue.TWO)
                .withCamera(NormalizedValue.ONE);

        //Convert JSON to Smartphone object
        Smartphone iPhone4Duplicate = new ObjectMapper().reader().forType(Smartphone.class).readValue(IPHONE4);

        assertNotNull(iPhone4Duplicate);
        assertEquals(iPhone4, iPhone4Duplicate);
    }
}
