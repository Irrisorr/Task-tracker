package main.java.runner;

public interface Runner {

    void run();

    void add();

    void update(long id);

    void delete(long id);

    void list();

    //TODO: implement another methods such as markInProgress, markToDo, markDone
}
