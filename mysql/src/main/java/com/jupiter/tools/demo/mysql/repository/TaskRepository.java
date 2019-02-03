package com.jupiter.tools.demo.mysql.repository;


import com.jupiter.tools.demo.mysql.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
public interface TaskRepository extends JpaRepository<Task, UUID> {

    Task findTopByNameContains(String name);

    @Query(value = "SELECT JSON_OBJECT('id', 87, 'name', 'prime')", nativeQuery = true)
    String nativeJson();
}
