package com.jupiter.tools.demo.postgres.service;

import com.jupiter.tools.demo.postgres.model.Task;
import com.jupiter.tools.demo.postgres.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
@Service
@RequiredArgsConstructor
public class SimpleTaskService implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public Task create(String name) {
        return taskRepository.save(Task.builder()
                                       .name(name)
                                       .build());
    }
}
