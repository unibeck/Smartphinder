package com.unibeck.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by jbeckman on 11/19/2016.
 */
public class UserConstraintJsonTest {

    private static final String CONSTRAINT =
            "{\"brand\":\"GOOGLE\",\"os\":\"ANDROID\",\"price\":799,\"battery\":3450,\"camera\":12,\"ram\":4.0,"+
            "\"storage\":128,\"resolution\":534,\"display-size\":5.5}";

    @Test
    public void basicSerializationWorks() throws JsonProcessingException {
        UserConstraint constraint = new UserConstraint(
                Brand.GOOGLE, OS.ANDROID, 799, 3450, 12, 4.0, 128, 534, 5.5
        );

        String serialized = new ObjectMapper().writeValueAsString(constraint);

        assertEquals(CONSTRAINT, serialized);
    }

    @Test
    public void basicDeserializationWorks() throws Exception {
        UserConstraint constraint = new UserConstraint(
                Brand.GOOGLE, OS.ANDROID, 799, 3450, 12, 4.0, 128, 534, 5.5
        );

        //Convert JSON to UserConstraint object
        UserConstraint constraintDuplicate = new ObjectMapper().reader().forType(UserConstraint.class).readValue(CONSTRAINT);

        assertNotNull(constraintDuplicate);
        assertEquals(constraint, constraintDuplicate);
    }
}
