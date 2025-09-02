import java.util.Scanner;

public class Duke {
    private static final String LINE =
            "____________________________________________________________";

    public static void main(String[] args) {
        System.out.println(LINE);
        System.out.println("Hello I'm mimi!");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        Scanner in = new Scanner(System.in);
        String line;
        Task[] list = new Task[100];
        int count = 0;
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
                if (count == 0) {
                    System.out.println("List is empty!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
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
            } else { // add new task
                System.out.println(LINE);
                System.out.println("added: " + line);
                System.out.println(LINE);
                list[count] = new Task(line);
                count++;
            }
        } while (in.hasNextLine());
    }
}
