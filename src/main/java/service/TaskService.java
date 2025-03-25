package main.java.service;

import main.java.model.Task;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TaskService implements TaskServiceInterface {

    private final Set<Task> tasks = new HashSet<Task>();
    private long id = 0;

    @Override
    public void addTask(String description) {
        Task task = new Task(this.id++, description);
        System.out.println("===> Task created successfully <===\n" + task.getFullInfo());
        tasks.add(task);
    }

    //DONE: implement updateTask method
    @Override
    public void updateTask(long id, String newDescription) {
        Task task = tasks.stream().filter(task1 -> task1.getId() == id).findFirst().orElse(null);

        if (task == null) {
            System.out.println("Task with id " + id + " not found");
        } else {
            task.setDescription(newDescription);
            task.setUpdatedAt(LocalDateTime.now());
            System.out.println("===> Task updated successfully <===\n" + task.getFullInfo());
        }

    }

    //TODO: implement deleteTask method
    @Override
    public void deleteTask(long id) {

    }

    //TODO: implement listTasks method in 2 ways: full info and description with id
    @Override
    public void listTasks() {
        tasks.stream().forEach(task -> System.out.println(task.getFullInfo()));
    }

}
