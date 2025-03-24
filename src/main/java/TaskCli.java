package main.java;

import main.java.service.TaskService;

public class TaskCli {

    public static void main(String[] args) {
        TaskService serv = new TaskService();
        serv.addTask("test");
        serv.addTask("test 2");
        serv.listTasks();
    }
}
