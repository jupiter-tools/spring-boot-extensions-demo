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
import org.springframework.web.bind.annotation.GetMapping;
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

        @Bean
        public ServerList<Server> customServerList() {
            return new TestCfg.CustomServerList();
        }

        @RestController
        @RequestMapping("/templates")
        public class TemplatesApi {

            @GetMapping("/make")
            public String make(@RequestParam("username") String username,
                               @RequestParam("balance") int balance) {
                return String.format("%s balance = %d", username, balance);
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