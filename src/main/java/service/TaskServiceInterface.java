package main.java.service;

public interface TaskServiceInterface {

    void addTask(String description);

    void updateTask(long id, String description);

    void deleteTask(long id);

    void listTasks();
}
