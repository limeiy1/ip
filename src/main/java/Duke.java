import java.util.Scanner;

public class Duke {
    private static final String LINE =
            "____________________________________________________________";
    private static final int MAX_TASKS = 100;
    private static int taskCount = 0;
    static Task[] taskList = new Task[MAX_TASKS];

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
                    throw new DukeException("Please enter a valid command :(");
                }
            } catch (DukeException e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
        } while (in.hasNextLine());
    }

    private static void printList() {
        System.out.println(LINE);
        if (taskCount == 0) {
            System.out.println("List is empty!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + taskList[i].toString());
            }
        }
        System.out.println(LINE);
    }

    private static void markTaskAsDone(String line) {
        int taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
        taskList[taskIndex].markAsDone();

        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskList[taskIndex].toString());
        System.out.println(LINE);
    }

    private static void unmarkTaskAsDone(String line) {
        int taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
        taskList[taskIndex].unmarkAsDone();

        System.out.println(LINE);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(taskList[taskIndex].toString());
        System.out.println(LINE);
    }

    private static void addTodo(String line) throws DukeException {
        if ((line.length() <= 5) || (line.substring(5).trim()).isEmpty()) {
            throw new DukeException("The todo description cannot be empty!");
        }
        String taskName = line.substring(5);
        taskList[taskCount] = new Todo(taskName);

        printAddedTask();
        taskCount++;
    }

    private static void addDeadline(String line) throws DukeException {
        if ((line.length() <= 9) || (line.substring(9).trim()).isEmpty()) {
            throw new DukeException("The deadline description cannot be empty!");
        }
        String deadlineSubstring = line.substring(9);
        String[] deadlineDetails = deadlineSubstring.split(" /by ");

        if (deadlineDetails.length != 2) {
            throw new DukeException("The deadline format is incorrect!");
        }
        String taskName = deadlineDetails[0];
        String deadlineDate = deadlineDetails[1];

        if (taskName.trim().isEmpty()) {
            throw new DukeException("The deadline task cannot be empty!");
        }
        if (deadlineDate.trim().isEmpty()) {
            throw new DukeException("The deadline date cannot be empty!");
        }

        taskList[taskCount] = new Deadline(taskName, deadlineDate);

        printAddedTask();
        taskCount++;
    }

    private static void addEvent(String line) throws DukeException {
        if ((line.length() <= 6) || (line.substring(6).trim()).isEmpty()) {
            throw new DukeException("The event description cannot be empty!");
        }
        String eventSubstring = line.substring(6);
        String[] eventDetails = eventSubstring.split(" /from ");
        String taskName = eventDetails[0];
        String[] eventDates = eventDetails[1].split(" /to ");
        String fromDate = eventDates[0];
        String toDate = eventDates[1];
        taskList[taskCount] = new Event(taskName, fromDate, toDate);

        printAddedTask();
        taskCount++;
    }

    private static void printAddedTask() {
        System.out.println(LINE);
        System.out.println("I've added this task:");
        System.out.println(taskList[taskCount].toString());
        if (taskCount == 0) {
            System.out.println("Now you have 1 task in your list.");
        } else {
            System.out.println("Now you have " + (taskCount + 1) + " tasks in your list.");
        }
        System.out.println(LINE);
    }
}
