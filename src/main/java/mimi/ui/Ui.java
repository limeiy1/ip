package mimi;

import mimi.tasks.Task;

import java.util.Scanner;

public class Ui {
    static final String LINE =
            "____________________________________________________________";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Mimi");
        System.out.println("What can I do for you?");
        showLine();
    }

    public static void showBye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public static void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLoadingError() {
        showLine();
        System.out.println("Error loading file. Starting with an empty task list.");
        showLine();
    }

    public void showError(String message) {
        showLine();
        System.out.println(message);
        showLine();
    }

    public static void showTaskList(TaskList tasks) {
        showLine();
        if (tasks.size() == 0) {
            System.out.println("Your task list is empty.");
        } else {
//            for (int i = 0; i < tasks.size(); i++) {
//                System.out.printf("%d.%s%n", i + 1, tasks.get(i));
//            }
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
        showLine();
    }

    public static void showAddedTask(Task task, int total) {
        showLine();
        System.out.println("I've added this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d task%s in the list.%n", total, total == 1 ? "" : "s");
        showLine();
    }

    public static void showDeletedTask(Task task, int total) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.printf("Now you have %d task%s in the list.%n", total, total == 1 ? "" : "s");
        showLine();
    }

    public static void showMark(Task taskName) {
        showLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskName);
        showLine();
    }

    public static void showUnmark(Task taskName) {
        showLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskName);
        showLine();
    }
}
