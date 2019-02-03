package com.jupiter.tools.demo.mysql.service;

import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.jupiter.tools.spring.test.mysql.annotation.meta.EnableMySqlIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@EnableMySqlIntegrationTest
class SimpleTaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    @ExpectedDataSet("datasets/expected.json")
    void create() {
        taskService.create("make good things");
    }
}