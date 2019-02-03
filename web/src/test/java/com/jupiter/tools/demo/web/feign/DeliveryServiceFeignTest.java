package com.jupiter.tools.demo.web.feign;

import com.jupiter.tools.spring.test.web.annotation.EnableEmbeddedWebServerTest;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerList;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

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

        @Bean
        public ServerList<Server> customServerList() {
            return new CustomServerList();
        }

        @RestController
        @RequestMapping("/delivery")
        public class DeliveryApi {

            @PostMapping("/send")
            public void send(@RequestParam(name = "text") String text) {
                assertThat(text).isEqualTo("123");
            }
        }

        public class CustomServerList extends AbstractServerList<Server> {

            private IClientConfig clientConfig;

            @Override
            public List<Server> getInitialListOfServers() {
                return getUpdatedListOfServers();
            }

            @Override
            public List<Server> getUpdatedListOfServers() {
                return Arrays.asList(new Server("127.0.0.1", Integer.valueOf(System.getProperty("server.port"))));
            }

            @Override
            public void initWithNiwsConfig(IClientConfig clientConfig) {
                this.clientConfig = clientConfig;
            }
        }
    }
}