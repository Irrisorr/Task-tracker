package main.java.service;

import main.java.model.Task;
import main.java.model.TaskStatus;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;


public class TaskService implements TaskServiceInterface {

    private final Set<Task> tasks;

    public TaskService(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    @Override
    public void addTask(String description) {
        Task task = new Task(getId(), description);
        System.out.println("===> Task created successfully <===\n" + task.getFullInfo());
        tasks.add(task);
    }

    @Override
    public void updateTask(long id, String newDescription) {
        Task task = tasks.stream().filter(task1 -> task1.getId() == id).findFirst().orElse(null);

        if (task == null) {
            System.out.println("Task with id=" + id + " not found\nPlease rerun command with proper id\n");
        } else {
            task.setDescription(newDescription);
            task.setUpdatedAt(LocalDateTime.now());
            System.out.println("===> Task updated successfully <===\n" + task.getFullInfo());
        }

    }

    @Override
    public void deleteTask(long id) {
        Task task = tasks.stream().filter(task1 -> task1.getId() == id).findFirst().orElse(null);
        if (task != null) {
            tasks.remove(task);
            System.out.println("===> Task deleted successfully <===\n");
        } else {
            System.out.println("Task with id=" + id + " not found\nPlease rerun command with proper id\n");
        }
    }

    @Override
    public void markTaskStatus(long id, TaskStatus status) {
        Task task = tasks.stream().filter(task1 -> task1.getId() == id).findFirst().orElse(null);
        if (task == null) {
            System.out.println("Task with id=" + id + " not found\nPlease rerun command with proper id\n");
        } else {
            switch (status) {
                case TODO -> task.setStatus(TaskStatus.TODO);
                case IN_PROGRESS -> task.setStatus(TaskStatus.IN_PROGRESS);
                case DONE -> task.setStatus(TaskStatus.DONE);
            }
            task.setUpdatedAt(LocalDateTime.now());
            System.out.println("===> Task status updated successfully <===\n" + task.getFullInfo());
        }
    }

    @Override
    public void listTasks(TaskStatus status) {

        if (status != null) {
            tasks.stream().sorted(Comparator.comparingLong(Task::getId).reversed())
                    .filter(task -> task.getStatus() == status)
                    .forEach(task -> System.out.println(task.getFullInfo()));
        } else {
            tasks.stream().sorted(Comparator.comparingLong(Task::getId).reversed())
                    .forEach(task -> System.out.println(task.getFullInfo()));
        }
    }

    private long getId() {
        return tasks.stream().map(Task::getId).max(Long::compare).orElse(0L) + 1;
    }
}
