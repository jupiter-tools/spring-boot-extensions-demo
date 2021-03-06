package com.jupiter.tools.demo.web.feign;


import com.jupiter.tools.spring.test.web.annotation.EnableEmbeddedWebServerTest;
import com.jupiter.tools.spring.test.web.extension.ribbon.RedirectRibbonToEmbeddedWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static com.jupiter.tools.demo.web.feign.DeliveryServiceFeignTest.TestCfg.messages;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@EnableEmbeddedWebServerTest
@RedirectRibbonToEmbeddedWebServer({"delivery-service", "test-service"})
class DeliveryServiceFeignTest {

    @Autowired
    private DeliveryServiceFeign deliveryServiceFeign;

    @Autowired
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        messages.clear();
    }

    @Test
    void redirectFeign() {
        deliveryServiceFeign.send("123");
        assertThat(messages).containsOnly("123");
    }

    @Test
    void redirectRestTemplate() {
        String message = "test-message";
        // Act
        String size = restTemplate.getForObject("http://test-service/messages/{message}/size",
                                                String.class,
                                                message);
        // Assert
        assertThat(TestCfg.messages).containsOnly(message);
        assertThat(Integer.valueOf(size)).isEqualTo(message.length());
    }

    @TestConfiguration
    public static class TestCfg {

        static List<String> messages = new ArrayList<>();

        @RestController
        @RequestMapping("/delivery")
        public class DeliveryApi {

            @PostMapping("/send")
            public void send(@RequestParam(name = "text") String text) {
                assertThat(text).isEqualTo("123");
                messages.add(text);
            }
        }

        @RestController
        @RequestMapping("/messages")
        public class TestApi {

            @GetMapping("/{message}/size")
            public String getLength(@PathVariable("message") String message) {
                messages.add(message);
                return String.valueOf(message.length());
            }
        }
    }
}