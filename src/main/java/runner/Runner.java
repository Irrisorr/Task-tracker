package main.java.runner;

import main.java.model.TaskStatus;


public interface Runner {

    String HELP_COMMAND = """
            Available commands: add, update $idTask, delete $idTask, list
            For exit type 'exit'
            """;

    void run();

    void add();

    void update(long id);

    void delete(long id);

    void markStatus(long id, TaskStatus status);

    void list(TaskStatus status);

    default long verifyId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            return -111;
        }
    }
}
