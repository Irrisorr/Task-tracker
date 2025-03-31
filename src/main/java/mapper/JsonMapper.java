package main.java.mapper;

import main.java.model.Task;
import main.java.model.TaskStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

//TODO: implement methods for store tasks at JSON file
public class JsonMapper {

    public void saveTasks(Set<Task> tasks) throws IOException {
        Path path = Path.of("tasks.json");

        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        Files.writeString(path, TasksToJson(tasks));
    }

    public Set<Task> loadTasks() throws IOException {
        Path path = Path.of("tasks.json");
        if (!Files.exists(path)) {
            return new HashSet<>();
        }
        return JsonToTasks(Files.readString(path));
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
        String[] allTasks = json.substring(2, json.length() - 2).split("},\\{");
        return Arrays.stream(allTasks)
                .map(this::JsonToTask)
                .collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        Task task = new Task(1, "description");
        JsonMapper jsonMapper = new JsonMapper();
        System.out.println(jsonMapper.TaskToJson(task));

        String json = "[{\"id\": \"1\", \"description\": \"descriptionName\", \"status\": \"TODO\", \"created_at\": \"2023-01-01T00:00:30\", \"updated_at\": \"2023-01-01T00:00:00\"}";
        String json1 = "{\"id\": \"2\", \"description\": \"descriptionName2\", \"status\": \"TODO\", \"created_at\": \"2023-01-01T00:00:00\"}]";
        String allJson = json + "," + json1;
        //Task task1 = jsonMapper.JsonToTask(json);
        //Task task2 = jsonMapper.JsonToTask(json1);
        //System.out.println(task1.getFullInfo());
        System.out.println(allJson);
        System.out.println(jsonMapper.JsonToTasks(allJson).stream().map(Task::getFullInfo).collect(Collectors.joining("\n")));

        Set<Task> tasks = new HashSet<>();
        tasks.add(task);
        //tasks.add(task2);
        System.out.println(jsonMapper.TasksToJson(tasks));

        System.out.println();
    }

}
