package main.java.runner;

import main.java.model.TaskStatus;

import java.io.IOException;

public interface Runner {

    String HELP_COMMAND = """
            Available commands: add, update $idTask, delete $idTask, list
            For exit type 'exit'
            """;

    void run() throws IOException;

    void add();

    void update(long id);

    void delete(long id);

    void markStatus(long id, TaskStatus status);

    void list();

    default long verifyId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            return -111;
        }
    }
}
