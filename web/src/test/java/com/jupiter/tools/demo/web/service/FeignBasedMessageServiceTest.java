package com.jupiter.tools.demo.web.service;

import com.jupiter.tools.demo.web.feign.DeliveryServiceFeign;
import com.jupiter.tools.demo.web.feign.TemplateServiceFeign;
import com.jupiter.tools.spring.test.core.annotation.EnableIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@EnableIntegrationTest
class FeignBasedMessageServiceTest {

    @Autowired
    private MessageService feignBasedMessageService;

    @MockBean
    private DeliveryServiceFeign deliveryServiceFeign;

    @MockBean
    private TemplateServiceFeign templateServiceFeign;

    @Test
    void send() {
        // Arrange
        when(templateServiceFeign.make(eq("user"), eq(123))).thenReturn("user balance = 123");
        // Act
        feignBasedMessageService.send("user", 123);
        // Asserts
        verify(templateServiceFeign).make(eq("user"), eq(123));
        verify(deliveryServiceFeign).send("user balance = 123");
    }
}