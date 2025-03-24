package main.java;

import main.java.service.TaskService;

import java.util.Scanner;

public class TaskCli {

    private static final TaskService serv = new TaskService();

    public static void main(String[] args) {
        run();
    }

    private static void run(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Available commands: add, update, delete, list\nFor exit type 'exit'\n>>> ");
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            switch (command) {
                case "add":
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    serv.addTask(description);
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
