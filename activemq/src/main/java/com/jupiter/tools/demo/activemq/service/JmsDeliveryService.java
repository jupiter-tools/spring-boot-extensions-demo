package com.jupiter.tools.demo.activemq.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@Service
@RequiredArgsConstructor
public class JmsDeliveryService implements DeliveryService {

    private final JmsTemplate jmsTemplate;

    @Override
    public void sendMessage(String message) {
        jmsTemplate.convertAndSend("test-queue", message);
    }
}
