import java.util.Scanner;
import java.util.ArrayList;

public class kx {

    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("  Hello! I'm kx, the kai xin bot!\n  What can I do for you?");
        System.out.println("____________________________________________________________");

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String[] input = scanner.nextLine().split(" ", 2);
            String command = input[0];


            if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("  Bye bye and hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            } else if (command.equals("list")) {
                listOut(tasks);

            } else if (command.equals("mark")) {
                Task task = tasks.get(Integer.parseInt(input[1]) - 1);
                System.out.println(task.markAsDone());

            } else if (command.equals("unmark")){
                Task task = tasks.get(Integer.parseInt(input[1]) - 1);
                System.out.println(task.markAsUndone());

            } else if (command.equals("deadline")) {

                String[] outputs = input[1].split(" /by ");
                Deadlines newTask = new Deadlines(outputs[0], outputs[1]);
                addTask(tasks, newTask);

            } else if (command.equals("todo")) {
                Todo newTask = new Todo (input[1]);
                addTask(tasks, newTask);

            } else if (command.equals("event")) {
                String[] outputs = input[1].split(" /from ");
                String[] outputs2 = outputs[1].split(" /to ");
                Events newTask = new Events(outputs[0], outputs2[0], outputs2[1]);
                addTask(tasks, newTask);

            }
        }
        scanner.close();

    }

    private static void listOut(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            System.out.println("  " + (i + 1) + ". " + curr.toString());
            }
        System.out.println("____________________________________________________________");
    }

    private static void addTask(ArrayList<Task> tasks, Task newTask) {
        tasks.add(newTask);
            System.out.println("____________________________________________________________");
            System.out.println("  Got it. I've added this task:");
            System.out.println("  " + newTask.toString());
            System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
            System.out.println("____________________________________________________________");
    }
}
