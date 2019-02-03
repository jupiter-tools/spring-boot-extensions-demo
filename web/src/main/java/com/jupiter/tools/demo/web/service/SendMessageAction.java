package com.jupiter.tools.demo.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@Service
@RequiredArgsConstructor
public class SendMessageAction {

    private final DeliveryServiceFeign deliveryServiceFeign;
    private final TemplateServiceFeign templateServiceFeign;

    public void send(String name, int value) {
        String message = templateServiceFeign.make(name, value);
        deliveryServiceFeign.send(message);
    }
}
