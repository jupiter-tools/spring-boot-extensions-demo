package com.jupiter.tools.demo.activemq;



import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 02.02.2019.
 *
 * @author Korovin Anatoliy
 */
@Configuration
public class Config {

    @Bean
    public Queue testQueue(){
        return new Queue("test-queue");
    }
}
