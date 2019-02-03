package com.jupiter.tools.demo.web.feign;

import com.jupiter.tools.demo.web.feign.DeliveryServiceFeign;
import com.jupiter.tools.spring.test.web.annotation.EnableEmbeddedWebServerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.web.bind.annotation.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@EnableEmbeddedWebServerTest
class DeliveryServiceFeignTest {

    @Autowired
    private DeliveryServiceFeign deliveryServiceFeign;

    @Test
    void send() {
        deliveryServiceFeign.send("123");
    }

    @TestConfiguration
    public static class TestCfg {

        @RestController
        @RequestMapping("/delivery")
        public class DeliveryApi {

            @PostMapping("/send")
            public void send(@RequestParam(name = "text") String text){
                assertThat(text).isEqualTo("123");
            }
        }
    }
}