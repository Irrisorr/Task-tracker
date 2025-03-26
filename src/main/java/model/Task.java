package main.java.model;

import java.time.LocalDateTime;

public class Task {

    private long id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(long id, String description) {
        this.id = id;
        this.description = description;
        this.status = TaskStatus.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFullInfo() {

        String info = String.format("Id: %d\nDescription: %s\nStatus: %s\nCreated at: %s\n",
                getId(), getDescription(), getStatus(), getCreatedAt());

        //Show updatedAt only if it is not null (not appeared at addTask method and appear at updateTask method)
        info = getUpdatedAt() == null ? info : info + "Updated at: " + getUpdatedAt() + "\n";

        return info;
    }
}
