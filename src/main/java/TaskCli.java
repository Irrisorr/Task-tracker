package main.java;

import main.java.service.TaskService;

import java.util.Scanner;

public class TaskCli {

    private static final TaskService serv = new TaskService();

    public static void main(String[] args) {
        run();
        //TODO: implement 1-line-cli method with arguments (like in the example)
    }

    private static void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Available commands: add, update $idTask, delete $idTask, list\nFor exit type 'exit'\n>>> ");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            String command = parts[0];
            String id = parts.length > 1 ? parts[1] : null;

            switch (command) {
                case "add":
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine().trim();
                    serv.addTask(description);
                    break;
                case "update":
                    System.out.print("Enter new task description: ");
                    String newDescription = scanner.nextLine().trim();
                    serv.updateTask(Long.parseLong(id), newDescription);
                    break;
                case "delete":
                    serv.deleteTask(Long.parseLong(id));
                    break;
                case "list":
                    serv.listTasks();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
            }

            System.out.print(">>> ");
        }
    }
}
