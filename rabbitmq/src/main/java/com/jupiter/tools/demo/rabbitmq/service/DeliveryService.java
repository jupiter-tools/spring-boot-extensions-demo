package com.jupiter.tools.demo.rabbitmq.service;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
public interface DeliveryService {

    void sendMessage(String message);
}
