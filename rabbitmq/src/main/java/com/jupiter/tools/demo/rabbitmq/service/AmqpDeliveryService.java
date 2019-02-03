package com.jupiter.tools.demo.rabbitmq.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@Service
@RequiredArgsConstructor
public class AmqpDeliveryService implements DeliveryService {

    private final AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String message) {
        amqpTemplate.convertAndSend("test-queue", message);
    }
}
