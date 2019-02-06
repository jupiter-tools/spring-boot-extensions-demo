package com.jupiter.tools.demo.web.feign;

import com.jupiter.tools.spring.test.web.annotation.EnableEmbeddedWebServerTest;
import com.jupiter.tools.spring.test.web.extension.ribbon.RedirectRibbonToEmbeddedWebServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@EnableEmbeddedWebServerTest
@RedirectRibbonToEmbeddedWebServer
class TemplateServiceFeignTest {

    @Autowired
    private TemplateServiceFeign templateServiceFeign;

    @Test
    void send() {
        String txt = templateServiceFeign.make("Trump", 1000);
        assertThat(txt).isEqualTo("Trump balance = 1000");
    }

    @TestConfiguration
    public static class TestCfg {

        @RestController
        @RequestMapping("/templates")
        public class TemplatesApi {

            @GetMapping("/make")
            public String make(@RequestParam("username") String username,
                               @RequestParam("balance") int balance) {
                return String.format("%s balance = %d", username, balance);
            }
        }
    }

}