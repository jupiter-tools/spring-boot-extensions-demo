package com.jupiter.tools.demo.web.service;

import com.jupiter.tools.demo.web.feign.DeliveryServiceFeign;
import com.jupiter.tools.demo.web.feign.TemplateServiceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@Service
@RequiredArgsConstructor
public class FeignBasedMessageService implements MessageService {

    private final DeliveryServiceFeign deliveryServiceFeign;
    private final TemplateServiceFeign templateServiceFeign;

    @Override
    public void send(String name, int value) {
        String message = templateServiceFeign.make(name, value);
        deliveryServiceFeign.send(message);
    }
}
