package mimi.ui;

import mimi.TaskList;
import mimi.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all user interactions including reading user input and printing formatted output.
 */
public class Ui {
    static final String LINE =
            "____________________________________________________________";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Mimi.");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showBye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("Error loading file. Starting with an empty task list.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("Your task list is empty. Start adding to your list now!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public void showAddedTask(Task task, int total) {
        System.out.println("I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d task%s in the list.%n", total, total == 1 ? "" : "s");
    }

    public void showDeletedTask(Task task, int total) {
        System.out.println("I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d task%s in the list.%n", total, total == 1 ? "" : "s");
    }

    public void showMarked(Task taskName) {
        System.out.println("Yay! I've marked this task as done:");
        System.out.println(taskName);
    }

    public void showUnmarked(Task taskName) {
        System.out.println("Aw ok, I've marked this task as not done yet:");
        System.out.println(taskName);
    }

    public void showFoundTasks(ArrayList<Task> matches) {
        if (matches.isEmpty()) {
            System.out.println("There are no matching tasks in the list.");
        } else {
            System.out.println("Here are the matching tasks in the list:");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println((i + 1) + ". " + matches.get(i));
            }
        }
    }
}
