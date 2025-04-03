package main.java.service;

import main.java.model.TaskStatus;

public interface TaskServiceInterface {

    void addTask(String description);

    void updateTask(long id, String description);

    void deleteTask(long id);

    void markTaskStatus(long id, TaskStatus status);

    void listTasks();
}
