package main.java.service;

import main.java.model.Task;

import java.util.HashSet;
import java.util.Set;

public class TaskService implements TaskServiceInterface {

    private final Set<Task> tasks = new HashSet<Task>();
    private long id = 0;

    @Override
    public void addTask(String description) {
        Task task = new Task(this.id++, description);
        tasks.add(task);
    }

    @Override
    public void updateTask(long id, String description) {

    }

    @Override
    public void deleteTask(long id) {

    }

    @Override
    public void listTasks() {
        tasks.stream().forEach(task -> System.out.println(task.getDescription()));
    }


}
