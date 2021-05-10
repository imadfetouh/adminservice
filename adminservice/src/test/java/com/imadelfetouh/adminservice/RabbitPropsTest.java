package com.imadelfetouh.adminservice;

import com.imadelfetouh.adminservice.rabbit.RabbitProps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RabbitPropsTest {

    @Test
    void testCorrId() {
        String corrId = RabbitProps.getInstance().getCorrId();

        Assertions.assertEquals("adminservice", corrId);
    }
}
