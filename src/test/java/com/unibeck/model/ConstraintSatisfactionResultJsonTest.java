package com.unibeck.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jbeckman on 11/19/2016.
 */
public class ConstraintSatisfactionResultJsonTest {

    private static final String CSR =
            "{\"remainder\":" +
                "[" +
                    "{" +
                        "\"device-name\":\"1phone1\",\"brand\":\"APPLE\",\"os\":\"iOS\",\"price\":\"ONE\","+
                        "\"display-size\":\"ONE\",\"resolution\":\"ONE\",\"ram\":\"ONE\",\"storage\":\"ONE\","+
                        "\"battery\":\"TWO\",\"camera\":\"ONE\"" +
                    "}," +
                    "{" +
                        "\"device-name\":\"2phone2\",\"brand\":\"APPLE\",\"os\":\"iOS\",\"price\":\"ONE\","+
                        "\"display-size\":\"ONE\",\"resolution\":\"ONE\",\"ram\":\"ONE\",\"storage\":\"ONE\","+
                        "\"battery\":\"TWO\",\"camera\":\"ONE\"" +
                    "}" +
                "]," +
                "\"constraintsUsed\":" +
                    "[" +
                        "true,true,false,false,true,false" +
                    "]" +
            "}";

    @Test
    public void basicSerializationWorks() throws JsonProcessingException {
        List<Smartphone> remainder = new ArrayList<>();
        remainder.add(new Smartphone()
                .withName("1phone1")
                .withBrand(Brand.APPLE)
                .withOperatingSystem(OS.iOS)
                .withPrice(NormalizedValue.ONE)
                .withDisplaySize(NormalizedValue.ONE)
                .withDisplayResolution(NormalizedValue.ONE)
                .withRam(NormalizedValue.ONE)
                .withStorage(NormalizedValue.ONE)
                .withBattery(NormalizedValue.TWO)
                .withCamera(NormalizedValue.ONE));

        remainder.add(new Smartphone()
                .withName("2phone2")
                .withBrand(Brand.APPLE)
                .withOperatingSystem(OS.iOS)
                .withPrice(NormalizedValue.ONE)
                .withDisplaySize(NormalizedValue.ONE)
                .withDisplayResolution(NormalizedValue.ONE)
                .withRam(NormalizedValue.ONE)
                .withStorage(NormalizedValue.ONE)
                .withBattery(NormalizedValue.TWO)
                .withCamera(NormalizedValue.ONE));

        boolean[] constraintsUsed = {true, true, false, false, true, false};

        ConstraintSatisfactionResult csr = new ConstraintSatisfactionResult(remainder, constraintsUsed);

        String serialized = new ObjectMapper().writeValueAsString(csr);

        assertEquals(CSR, serialized);
    }

    @Test
    public void basicDeserializationWorks() throws Exception {
        List<Smartphone> remainder = new ArrayList<>();
        remainder.add(new Smartphone()
                .withName("1phone1")
                .withBrand(Brand.APPLE)
                .withOperatingSystem(OS.iOS)
                .withPrice(NormalizedValue.ONE)
                .withDisplaySize(NormalizedValue.ONE)
                .withDisplayResolution(NormalizedValue.ONE)
                .withRam(NormalizedValue.ONE)
                .withStorage(NormalizedValue.ONE)
                .withBattery(NormalizedValue.TWO)
                .withCamera(NormalizedValue.ONE));

        remainder.add(new Smartphone()
                .withName("2phone2")
                .withBrand(Brand.APPLE)
                .withOperatingSystem(OS.iOS)
                .withPrice(NormalizedValue.ONE)
                .withDisplaySize(NormalizedValue.ONE)
                .withDisplayResolution(NormalizedValue.ONE)
                .withRam(NormalizedValue.ONE)
                .withStorage(NormalizedValue.ONE)
                .withBattery(NormalizedValue.TWO)
                .withCamera(NormalizedValue.ONE));

        boolean[] constraintsUsed = {true, true, false, false, true, false};

        ConstraintSatisfactionResult csr = new ConstraintSatisfactionResult(remainder, constraintsUsed);

        //Convert JSON to Smartphone object
        ConstraintSatisfactionResult csrDuplicate = new ObjectMapper().reader().forType(ConstraintSatisfactionResult.class).readValue(CSR);

        assertNotNull(csrDuplicate);
        assertEquals(csr, csrDuplicate);
    }
}
