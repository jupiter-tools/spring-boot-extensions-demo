package com.jupiter.tools.demo.postgres.service;

import com.jupiter.tools.demo.postgres.model.Task;

/**
 * Created on 03.02.2019.
 *
 * @author Korovin Anatoliy
 */
public interface TaskService {

    Task create(String name);
}
