package main.java.runner;

public interface Runner {

    void run();

    void add();

    void update(long id);

    void delete(long id);

    void list();

    default long verifyId(String id) {
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException e) {
            return -111;
        }
    }

    //TODO: implement another methods such as markInProgress, markToDo, markDone
}
