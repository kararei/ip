import java.util.Scanner;
import java.util.ArrayList;

public class kx {

    private static void listOut(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + ". [" + tasks.get(i).getStatusIcon() + "] " +tasks.get(i).description);
            }
        System.out.println("____________________________________________________________");
    }

    private static void addTask(ArrayList<Task> tasks, String input) {
        tasks.add(new Task(input));
            System.out.println("____________________________________________________________");
            System.out.println("  added: " + input);
            System.out.println("____________________________________________________________");
    }



    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("  Hello! I'm kx, the kai xin bot!\n  What can I do for you?");
        System.out.println("____________________________________________________________");

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String[] commands = input.split(" ");
            String command = commands[0];


            if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("  Bye bye and hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            } else if (command.equals("list")) {
                listOut(tasks);

            } else if (command.equals("mark")) {
                Task task = tasks.get(Integer.parseInt(commands[1]) - 1);
                System.out.println(task.markAsDone());

            } else if (command.equals("unmark")){
                Task task = tasks.get(Integer.parseInt(commands[1]) - 1);
                System.out.println(task.markAsUndone());

            } else {
                addTask(tasks, command);
            }
        }
        scanner.close();

    }
}
