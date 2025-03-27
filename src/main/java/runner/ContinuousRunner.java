package main.java.runner;

import main.java.service.TaskService;

import java.util.Scanner;

public class ContinuousRunner implements Runner{

    private final TaskService serv = new TaskService();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        System.out.print("Available commands: add, update $idTask, delete $idTask, list\nFor exit type 'exit'\n>>> ");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            String command = parts[0];
            String id = parts.length > 1 ? parts[1] : null;

            switch (command) {
                case "add" -> add();
                case "update" -> update(verifyId(id));
                case "delete" -> delete(verifyId(id));
                case "list" -> list();
                case "exit" -> exit();
            }

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
        //TODO: in future rewrite verifyId method to have less garbage
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
    public void list() {
        serv.listTasks();
    }

    public void exit(){
        scanner.close();
        System.exit(0);
    }
}
