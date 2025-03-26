package main.java;

import main.java.runner.ContinuousRunner;
import main.java.service.TaskService;

import java.util.Scanner;

public class TaskCli {

    private static final ContinuousRunner continuousRunner = new ContinuousRunner();

    public static void main(String[] args) {
        continuousRunner.run();
        //TODO: implement 1-line-cli method with arguments (like in the example)
    }


}
