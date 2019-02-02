package com.jupiter.tools.demo.postgres.repository;

import com.jupiter.tools.demo.postgres.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
public interface TaskRepository extends JpaRepository<Task, UUID> {

    Task findTopByNameContains(String name);
}
