package main.java.runner;

import main.java.mapper.JsonMapper;
import main.java.model.TaskStatus;
import main.java.service.TaskService;

import java.io.IOException;
import java.util.Scanner;

public class ContinuousRunner implements Runner{

    private final Scanner scanner = new Scanner(System.in);
    private final JsonMapper jsonMapper = new JsonMapper();
    private final TaskService serv;

    {
        try {
            serv = new TaskService(jsonMapper.loadTasks());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {
        System.out.print(HELP_COMMAND + ">>> ");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            String command = parts[0];
            String id = parts.length > 1 ? parts[1] : null;

            switch (command) {
                case "add" -> add();
                case "update" -> update(verifyId(id));
                case "delete" -> delete(verifyId(id));
                case "mark-in-progress" -> markStatus(verifyId(id), TaskStatus.IN_PROGRESS);
                case "mark-done" -> markStatus(verifyId(id), TaskStatus.DONE);
                case "mark-todo" -> markStatus(verifyId(id), TaskStatus.TODO);
                case "list" -> list();
                case "exit" -> exit();
                default -> System.out.println("Unknown command.\n" + HELP_COMMAND);
            }

            synchronize();
            System.out.print(">>> ");
        }
    }

    @Override
    public void add() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine().trim();
        serv.addTask(description);
    }

    @Override
    public void update(long id) {
        if (id == -111) {
            System.out.println("Please type valid id. Must be a number\n");
        } else {
            System.out.print("Enter new task description: ");
            String newDescription = scanner.nextLine().trim();
            serv.updateTask(id, newDescription);
        }
    }

    @Override
    public void delete(long id) {
        if (id == -111) {
            System.out.println("Please type valid id. Must be a number\n");
        } else serv.deleteTask(id);
    }

    @Override
    public void markStatus(long id, TaskStatus status) {
        serv.markTaskStatus(id, status);
    }

    @Override
    public void list() {
        serv.listTasks();
    }

    public void exit()  {
        synchronize();
        scanner.close();
        System.exit(0);
    }

    private void synchronize() {
        try {
            jsonMapper.saveTasks(serv.getTasks());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
