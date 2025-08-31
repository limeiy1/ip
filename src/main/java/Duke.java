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
        String[] list = new String[100];
        int count = 0;
        do {
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                System.out.println(LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(LINE);
                return;
            }
            if (line.equalsIgnoreCase("list")) {
                System.out.println(LINE);
                if (count == 0) {
                    System.out.println("List is empty!");
                } else {
                    for (int i = 0; i < count; i++) {
                        System.out.println( (i+1) + ". " + list[i]);
                    }
                }
                System.out.println(LINE);
            } else {
                System.out.println(LINE);
                System.out.println("added: " + line);
                System.out.println(LINE);
                list[count] = line;
                count++;
            }
        } while (in.hasNextLine());
    }
}
