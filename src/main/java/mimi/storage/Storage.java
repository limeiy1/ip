package mimi.storage;

import mimi.exception.MimiException;
import mimi.tasks.Deadline;
import mimi.tasks.Event;
import mimi.tasks.Task;
import mimi.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws MimiException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                throw new MimiException("Failed to create directory: " + parentDir.getAbsolutePath());
            }
        }

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseData(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new MimiException("Error reading save file: " + e.getMessage());
        }
        return tasks;

    }

    private static Task parseData(String line) throws MimiException {
        String[] word = line.split("\\|");
        if (word.length < 3) {
            throw new MimiException("Line is corrupted.");
        }

        String taskType = word[0].trim();
        String doneFlag = word[1].trim();

        switch (taskType) {
        case "T": {
            Task todo = new Todo(word[2].trim());
            if (doneFlag.equals("1")) {
                todo.markAsDone();
            }
            return todo;
        }
        case "D": {
            if (word.length != 4) {
                throw new MimiException("Invalid deadline line: " + line);
            }
            Task deadline = new Deadline(word[2].trim(), word[3].trim());
            if (doneFlag.equals("1")) {
                deadline.markAsDone();
            }
            return deadline;
        }
        case "E": {
            if (word.length != 5) {
                throw new MimiException("Invalid event line: " + line);
            }
            Task event = new Event(word[2].trim(), word[3].trim(), word[4].trim());
            if (doneFlag.equals("1")) {
                event.markAsDone();
            }
            return event;
        }
        default:
            throw new MimiException("Invalid task type: " + taskType);
        }
    }

    public void save(ArrayList<Task> taskList) {
        try (FileWriter newFileWriter = new FileWriter(filePath)) {
            for (Task task : taskList) {
                newFileWriter.write(task.toSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
