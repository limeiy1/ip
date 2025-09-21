package mimi;

import mimi.tasks.Task;
import mimi.tasks.Todo;
import mimi.tasks.Deadline;
import mimi.tasks.Event;

import mimi.exception.MimiException;

import java.util.Scanner;
import java.util.ArrayList;

public class Mimi {
    private static final String LINE =
            "____________________________________________________________";
    static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("Hello I'm mimi!");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        Scanner in = new Scanner(System.in);
        String inputLine;
        do {
            inputLine = in.nextLine();
            try {
                if (inputLine.equalsIgnoreCase("bye")) {
                    System.out.println(LINE);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    return;
                }
                if (inputLine.equalsIgnoreCase("list")) { // show list
                    printList();
                } else if (inputLine.startsWith("mark")) { // mark as done
                    markTaskAsDone(inputLine);
                } else if (inputLine.startsWith("unmark")) { // unmark as done
                    unmarkTaskAsDone(inputLine);
                } else if (inputLine.startsWith("todo")) { // add new todo task
                    addTodo(inputLine);
                } else if (inputLine.startsWith("deadline")) { // add new deadline task
                    addDeadline(inputLine);
                } else if (inputLine.startsWith("event")) { // add new event task
                    addEvent(inputLine);
                } else { // print a statement when an invalid command is received
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

    private static void markTaskAsDone(String line) {
        int taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
        taskList.get(taskIndex).markAsDone();

        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList.get(taskIndex).toString());
        System.out.println(LINE);
    }

    private static void unmarkTaskAsDone(String line) {
        int taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
        taskList.get(taskIndex).unmarkAsDone();

        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList.get(taskIndex).toString());
        System.out.println(LINE);
    }

    private static void addTodo(String line) throws MimiException {
        if ((line.length() <= 5) || (line.substring(5).trim()).isEmpty()) {
            throw new MimiException("The todo description cannot be empty!");
        }
        String taskName = line.substring(5);
        taskList.add(new Todo(taskName));

        printAddedTask();
    }

    private static void addDeadline(String line) throws MimiException {
        if ((line.length() <= 9) || (line.substring(9).trim()).isEmpty()) {
            throw new MimiException("The deadline description cannot be empty!");
        }
        String deadlineSubstring = line.substring(9);
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
    }

    private static void addEvent(String line) throws MimiException {
        if ((line.length() <= 6) || (line.substring(6).trim()).isEmpty()) {
            throw new MimiException("The event description cannot be empty!");
        }
        String eventSubstring = line.substring(6);
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
