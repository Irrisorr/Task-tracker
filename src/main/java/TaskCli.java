package main.java;

import main.java.runner.ArgumentsRunner;
import main.java.runner.ContinuousRunner;
import main.java.runner.Runner;

public class TaskCli {

    private static final Runner continuousRunner = new ContinuousRunner();
    private static final ArgumentsRunner argumentsRunner = new ArgumentsRunner();

    public static void main(String[] args) {
//        continuousRunner.run();
        argumentsRunner.run(args);
    }


}
