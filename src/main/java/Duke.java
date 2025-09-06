import java.util.Scanner;

public class Duke {
    private static final String LINE =
            "____________________________________________________________";
    private static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("Hello I'm mimi!");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        Scanner in = new Scanner(System.in);
        String line;
        Task[] list = new Task[MAX_TASKS];
        int numberOfTasks = 0;
        do {
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                return;
            }
            if (line.equalsIgnoreCase("list")) { // show list
                System.out.println(LINE);
                if (numberOfTasks == 0) {
                    System.out.println("List is empty!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < numberOfTasks; i++) {
                        System.out.println((i + 1) + ". " + list[i].toString());
                    }
                }
                System.out.println(LINE);
            } else if (line.startsWith("mark")) { // mark as done
                int taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                list[taskIndex].markAsDone();
                System.out.println(LINE);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[taskIndex].toString());
                System.out.println(LINE);
            } else if (line.startsWith("unmark")) { // unmark as done
                int taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                list[taskIndex].unmarkAsDone();
                System.out.println(LINE);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list[taskIndex].toString());
                System.out.println(LINE);
            } else {
                if (line.startsWith("todo")){ // add new todo
                    String taskName = line.substring(5); // check correctness
                    list[numberOfTasks] = new Todo(taskName);

                } else if (line.startsWith("deadline")){ //add new deadline
                    String task = line.substring(9);
                    String[] parts = task.split(" /by ");
                    String taskName = parts[0];
                    String deadline = parts[1];
                    list[numberOfTasks] = new Deadline(taskName, deadline);

                } else if (line.startsWith("event")){
                    String task = line.substring(6);
                    String[] parts = task.split(" /from ");
                    String taskName = parts[0];
                    String[] toSplit = parts[1].split(" /to ");
                    String from = toSplit[0];
                    String to = toSplit[1];
                    list[numberOfTasks] = new Event(taskName, from, to);
                }
                System.out.println(LINE);
                System.out.println("I've added this task:");
                System.out.println(list[numberOfTasks].toString());
                System.out.println("Now you have " + (numberOfTasks + 1) + " tasks in your list.");
                System.out.println(LINE);
                numberOfTasks++;
            }
        } while (in.hasNextLine());
    }
}
