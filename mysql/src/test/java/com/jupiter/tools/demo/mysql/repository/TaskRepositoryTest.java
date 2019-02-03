package com.jupiter.tools.demo.mysql.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.DataSetFormat;
import com.github.database.rider.core.api.exporter.ExportDataSet;

import com.jupiter.tools.demo.mysql.model.Task;
import com.jupiter.tools.spring.test.mysql.annotation.meta.EnableMySqlDataTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@EnableMySqlDataTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Disabled("used just to make a dataset")
    @Test
    @Commit
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @DataSet(cleanBefore = true, cleanAfter = true)
    @ExportDataSet(outputName = "./target/dataset/export.json", format = DataSetFormat.JSON)
    void export() {
        taskRepository.save(Task.builder().name("first task").build());
        taskRepository.save(Task.builder().name("second task").build());
    }

    @Test
    @DataSet(value = "/datasets/task_list.json", cleanBefore = true, cleanAfter = true)
    void findByNameTest() {
        // Arrange
        // Act
        Task first = taskRepository.findTopByNameContains("first");
        // Asserts
        assertThat(first).isNotNull()
                         .extracting(Task::getId,
                                     Task::getName)
                         .containsOnly(UUID.fromString("d4054617-e11d-4a2c-bbfd-fff27501e1ce"),
                                       "first task");
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true)
    void nativeJsonQuery() {
        assertThat(taskRepository.nativeJson()).isNotNull();
    }
}