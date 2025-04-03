package main.java.mapper;

import main.java.model.Task;
import main.java.model.TaskStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class JsonMapper {

    private static final Path TASKS_JSON_PATH = Path.of("../tasks.json");

    public void saveTasks(Set<Task> tasks) throws IOException {
        if (!Files.exists(TASKS_JSON_PATH)) {
            Files.createFile(TASKS_JSON_PATH);
        }
        Files.writeString(TASKS_JSON_PATH, TasksToJson(tasks));
    }

    public Set<Task> loadTasks() throws IOException {
        if (!Files.exists(TASKS_JSON_PATH)) {
            return new HashSet<>();
        }
        
        String content = Files.readString(TASKS_JSON_PATH);
        if (content.equals("[]")) {
            return new HashSet<>();
        }
        
        return JsonToTasks(content);
    }

    private String TaskToJson(Task task) {
        return "{\"id\": \"" + task.getId() +
                "\", \"description\": \"" + task.getDescription() +
                "\", \"status\": \"" + task.getStatus() +
                "\", \"created_at\": \"" + task.getCreatedAt() +
                "\", \"updated_at\": \"" + task.getUpdatedAt() + "\"}";
    }

    private String TasksToJson(Set<Task> tasks) {
        return tasks.stream()
                .map(this::TaskToJson)
                .collect(Collectors.joining(",", "[", "]"));
    }

    private Task JsonToTask(String json) {
        String[] allTasks = json
                .replaceAll("\"", "")
                .split(",");

        List<String> result = new ArrayList<>();
        for (String pair: allTasks) {
            String value = pair.substring(pair.indexOf(":") + 1);
            result.add(value.trim());
        }

        long id = Long.parseLong(result.get(0));
        String description = result.get(1);
        TaskStatus status = TaskStatus.valueOf(result.get(2));
        LocalDateTime createdAt = LocalDateTime.parse(result.get(3));
        LocalDateTime updatedAt = (result.size() < 5 || result.get(4).equals("null")) ? null : LocalDateTime.parse(result.get(4));

        return new Task(id, description, status, createdAt, updatedAt);
    }

    private Set<Task> JsonToTasks(String json) {
        if (json.trim().length() <= 2) {
            return new HashSet<>();
        }

        String[] allTasks = json.substring(2, json.length() - 2)
                .split("},\\{");
        return Arrays.stream(allTasks)
            .map(this::JsonToTask)
            .collect(Collectors.toSet());
    }
}
