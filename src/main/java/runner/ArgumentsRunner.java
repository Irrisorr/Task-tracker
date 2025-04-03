package main.java.runner;

import main.java.mapper.JsonMapper;
import main.java.model.TaskStatus;
import main.java.service.TaskService;

import java.io.IOException;

public class ArgumentsRunner implements Runner{

    private String description;

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
    public void run(){
    }

    public void run(String[] args) {
        try {
            if (args.length == 0) {
                System.out.println("Please type valid command.\n" + HELP_COMMAND);
                return;
            }

            String command = args[0];
            long id;

            switch (command) {
                case "add" -> {
                    description = args[1];
                    add();
                }
                case "update" -> {
                    id = verifyId(args[1]);
                    description = args[2];
                    update(id);
                }
                case "delete" -> {
                    id = verifyId(args[1]);
                    delete(id);
                }
                case "list" -> list();
                case "mark-in-progress" -> {
                    id = verifyId(args[1]);
                    markStatus(id, TaskStatus.IN_PROGRESS);
                }
                case "mark-done" -> {
                    id = verifyId(args[1]);
                    markStatus(id, TaskStatus.DONE);
                }
                case "mark-todo" -> {
                    id = verifyId(args[1]);
                    markStatus(id, TaskStatus.TODO);
                }
                default -> System.out.println("Unknown command.\n" + HELP_COMMAND);
            }
            synchronize();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please type valid command.\n" + HELP_COMMAND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add() {
        serv.addTask(description);
    }

    @Override
    public void update(long id) {
        if (id == -111) {
            System.out.println("Please type valid id. Must be a number\n");
        } else serv.updateTask(id, description);
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

    private void synchronize() throws IOException {
        jsonMapper.saveTasks(serv.getTasks());
    }
}
