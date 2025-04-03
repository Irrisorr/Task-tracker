# Task Tracker CLi

### Overview
A simple, lightweight Task Tracker application implemented in Java with a Command Line Interface (CLI). 
The project is designed to help users manage and track their tasks, offering features like adding, updating, viewing, and deleting tasks, all while storing data in a JSON file, ensuring your tasks are saved between sessions.

### Features

- Task Management: Create, update, view, and delete tasks.
- Task Status Tracking: Tasks can be marked as Todo, In-Progress, or Done using an intuitive CLI.
- Persistent Storage: Tasks are saved to a tasks.json file for persistent data storage.
- Minimal Dependencies: No external libraries are used, ensuring the project is simple and lightweight.

### How to Run

1. Clone the repository:
```
git clone https://github.com/Irrisorr/Task-tracker.git
cd Task-Tracker
javac main/java/TaskCli.java
```
2. Run TaskCli.java with arguments (1 method to run):
```
java main.java.TaskCli <command> [<id> <description>]
```
3. Without arguments (in cli environment) and after u can write command (examples below)
```
java main.java.TaskCli
```

### Usage examples

1. With arguments

Adding a new task
```
java main.java.TaskCli add "Buy groceries"
```
Updating and deleting tasks
```
java main.java.TaskCli update 1 "Buy groceries and cook dinner"
java main.java.TaskCli delete 1
```

Marking a task as in progress or done
```
java main.java.TaskCli mark-in-progress 1
java main.java.TaskCli mark-done 1
```

Listing all tasks
```
java main.java.TaskCli list
```

#### Listing tasks by status
```
java main.java.TaskCli list done
java main.java.TaskCli list todo
java main.java.TaskCli list in-progress
```

2. Without arguments

For the first time run:
```
java main.java.TaskCli
```

after that an internal environment will appear in which you can write commands the same as the arguments above.

Adding a new task (then you will need to enter a description)
```
add
```
Updating and deleting tasks (then you will need to enter a description)
```
update 1
delete 1
```

Marking a task as in progress or done
```
mark-in-progress 1
mark-done 1
```

Listing all tasks
```
list
```

#### Listing tasks by status
```
list-done
list-todo
list-in-progress
```
