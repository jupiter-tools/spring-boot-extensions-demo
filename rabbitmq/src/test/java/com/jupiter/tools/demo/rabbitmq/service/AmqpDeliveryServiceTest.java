package com.jupiter.tools.demo.rabbitmq.service;

import com.jupiter.tools.spring.test.rabbitmq.annotation.ExpectedMessage;
import com.jupiter.tools.spring.test.rabbitmq.annotation.meta.EnableRabbitMqTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@EnableRabbitMqTest
class AmqpDeliveryServiceTest {

    @Autowired
    private DeliveryService amqpDeliveryService;

    @Test
    @ExpectedMessage(queue = "test-queue", message = "secret text")
    void sendMessageTest() {
        amqpDeliveryService.sendMessage("secret text");
    }
}