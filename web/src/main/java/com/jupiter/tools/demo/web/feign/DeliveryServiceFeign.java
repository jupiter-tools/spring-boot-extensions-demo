package com.jupiter.tools.demo.web.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@FeignClient(name = "delivery-service", path = "delivery")
public interface DeliveryServiceFeign {

    @RequestMapping(method = RequestMethod.POST, value = "/send")
    void send(@RequestParam(name = "text") String text);
}
