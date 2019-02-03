package com.jupiter.tools.demo.activemq.service;


import com.jupiter.tools.spring.test.activemq.annotation.ExpectedMessage;
import com.jupiter.tools.spring.test.activemq.annotation.meta.EnableActiveMqTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@EnableActiveMqTest
class JmsDeliveryServiceTest {

    @Autowired
    private DeliveryService jmsDeliveryService;

    @Test
    @ExpectedMessage(queue = "test-queue", message = "secret text")
    void sendMessageTest() {
        jmsDeliveryService.sendMessage("secret text");
    }
}