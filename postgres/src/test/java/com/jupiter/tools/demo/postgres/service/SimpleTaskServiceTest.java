package com.jupiter.tools.demo.postgres.service;

import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@EnablePostgresIntegrationTest
class SimpleTaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    @ExpectedDataSet("datasets/expected.json")
    void create() {
        taskService.create("make good things");
    }
}