package mimi;

import mimi.tasks.Task;
import mimi.tasks.Todo;
import mimi.tasks.Deadline;
import mimi.tasks.Event;

import mimi.exception.MimiException;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Mimi {
    private static final String FILE_PATH = "./data/mimi.txt";

    private static final String LINE =
            "____________________________________________________________";
    static ArrayList<Task> taskList = new ArrayList<>();

    public static ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try {
                    tasks.add(parseData(line));
                } catch (MimiException e) {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
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

    public static void save(ArrayList<Task> taskList) {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task task : taskList) {
                fw.write(task.toSaveFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        try {
            taskList = load();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        System.out.println(LINE);
        System.out.println("Hello I'm mimi!");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        Scanner in = new Scanner(System.in);
        String inputLine;
        do {
            inputLine = in.nextLine();
            String[] parts = inputLine.split(" ", 2);
            String command = parts[0].toLowerCase();
            String lineBody = (parts.length == 2) ? parts[1] : "";
            try {
                switch (command) {
                case "bye":
                    System.out.println(LINE);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    return;
                case "list":
                    printList();
                    break;
                case "mark":
                    markTaskAsDone(lineBody);
                    break;
                case "unmark":
                    unmarkTaskAsDone(lineBody);
                    break;
                case "todo":
                    addTodo(lineBody);
                    break;
                case "deadline":
                    addDeadline(lineBody);
                    break;
                case "event":
                    addEvent(lineBody);
                    break;
                case "delete":
                    handleDelete(lineBody);
                    break;
                default:
                    throw new MimiException("Please enter a valid command :(");
                }
            } catch (MimiException e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
        } while (in.hasNextLine());
    }

    private static void printList() {
        System.out.println(LINE);
        if (taskList.isEmpty()) {
            System.out.println("List is empty!");
        } else {
            for (Task task : taskList) {
                System.out.println((taskList.indexOf(task)+1) + ". " + task.toString());
            }
        }
        System.out.println(LINE);
    }

    private static void markTaskAsDone(String lineBody) throws MimiException {
        if (lineBody.trim().isEmpty()) {
            throw new MimiException("Enter a task number!");
        }
        int taskIndex = Integer.parseInt(lineBody.trim()) - 1;
        if (taskIndex >= taskList.size()) {
            throw new MimiException("Task number is out of list range! Enter a valid task number!");
        }
        taskList.get(taskIndex).markAsDone();

        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(taskIndex).toString());
        System.out.println(LINE);

        save(taskList);
    }

    private static void unmarkTaskAsDone(String lineBody) throws MimiException {
        if (lineBody.trim().isEmpty()) {
            throw new MimiException("Enter a task number!");
        }
        int taskIndex = Integer.parseInt(lineBody.trim()) - 1;
        if (taskIndex >= taskList.size()) {
            throw new MimiException("Task number is out of list range! Enter a valid task number!");
        }
        taskList.get(taskIndex).unmarkAsDone();

        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(taskIndex).toString());
        System.out.println(LINE);

        save(taskList);
    }

    private static void addTodo(String lineBody) throws MimiException {
        String taskName = lineBody.trim();
        if (taskName.isEmpty()) {
            throw new MimiException("The todo description cannot be empty!");
        }
        taskList.add(new Todo(taskName));

        printAddedTask();

        save(taskList);
    }

    private static void addDeadline(String lineBody) throws MimiException {
        String deadlineSubstring = lineBody.trim();
        if (deadlineSubstring.isEmpty()) {
            throw new MimiException("The deadline description cannot be empty!");
        }

        String[] deadlineDetails = deadlineSubstring.split(" /by ");
        if (deadlineDetails.length != 2) {
            throw new MimiException("The deadline format is incorrect!");
        }

        String taskName = deadlineDetails[0];
        String deadlineDate = deadlineDetails[1];
        if (taskName.trim().isEmpty()) {
            throw new MimiException("The deadline task cannot be empty!");
        }
        if (deadlineDate.trim().isEmpty()) {
            throw new MimiException("The deadline date cannot be empty!");
        }

        taskList.add(new Deadline(taskName, deadlineDate));

        printAddedTask();

        save(taskList);
    }

    private static void addEvent(String lineBody) throws MimiException {
        String eventSubstring = lineBody.trim();
        if (eventSubstring.isEmpty()) {
            throw new MimiException("The event description cannot be empty!");
        }
        String[] eventDetails = eventSubstring.split(" /from ");
        if (eventDetails.length != 2) {
            throw new MimiException("The event format is incorrect!");
        }

        String eventName = eventDetails[0];
        if (eventName.trim().isEmpty()) {
            throw new MimiException("The event name cannot be empty!");
        }

        String[] eventDates = eventDetails[1].split(" /to ");
        if (eventDates.length != 2) {
            throw new MimiException("The event format is incorrect!");
        }

        String fromDate = eventDates[0];
        String toDate = eventDates[1];
        if (fromDate.trim().isEmpty() || toDate.trim().isEmpty()) {
            throw new MimiException("The event date cannot be empty!");
        }

        taskList.add(new Event(eventName, fromDate, toDate));

        printAddedTask();

        save(taskList);
    }

    private static void handleDelete(String lineBody) throws MimiException {
        if (lineBody.trim().isEmpty()) {
            throw new MimiException("Enter a task number!");
        }
        int taskIndex = Integer.parseInt(lineBody.trim()) - 1;
        if (taskIndex >= taskList.size()) {
            throw new MimiException("Task number is out of list range! Enter a valid task number!");
        }

        System.out.println(LINE);
        System.out.println("I've removed this task:");
        System.out.println(taskList.get(taskIndex).toString());

        taskList.remove(taskIndex);

        System.out.println("Now you have " + taskList.size() + " tasks in your list.");
        System.out.println(LINE);

        save(taskList);
    }

    private static void printAddedTask() {
        System.out.println(LINE);
        System.out.println("I've added this task:");
        System.out.println(taskList.get(taskList.size()-1).toString());
        if (taskList.size() == 1) {
            System.out.println("Now you have 1 task in your list.");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in your list.");
        }
        System.out.println(LINE);
    }

}
