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
        do {
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                return;
            }
            System.out.println(LINE);
            System.out.println(line);
            System.out.println(LINE);
        } while (in.hasNextLine());
    }
}
