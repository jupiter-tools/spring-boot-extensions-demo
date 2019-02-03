package com.jupiter.tools.demo.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@FeignClient(value = "template-service", path = "templates")
public interface TemplateServiceFeign {

    @GetMapping("make")
    String make(@RequestParam("username") String username,
                @RequestParam("balance") int balance);
}
