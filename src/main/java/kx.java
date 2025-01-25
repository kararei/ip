import java.util.Scanner;
import java.util.ArrayList;

public class kx {

    public static void main(String[] args) throws kxException {
        System.out.println("____________________________________________________________");
        System.out.println("  Hello! I'm kx, the kai xin bot!\n  What can I do for you?");
        System.out.println("____________________________________________________________");

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String[] input = scanner.nextLine().split(" ", 2);
            String command = input[0];

            if (input.length == 1 && !command.equals("list") && !command.equals("bye")) {
                throw new kxException("  ERROR! The description of a task cannot be empty.");
            }


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

                // check for /by
                if (!input[1].contains(" /by ")) {
                    throw new kxException("  ERROR! The description of a deadline must include /by.");
                }

                String[] outputs = input[1].split(" /by ");
                
                // check for both task and deadline
                if (outputs.length != 2) {
                    throw new kxException("  ERROR! The description must include both the task and the deadline.");
                }

                Deadlines newTask = new Deadlines(outputs[0], outputs[1]);
                addTask(tasks, newTask);

            } else if (command.equals("todo")) {
                Todo newTask = new Todo (input[1]);
                addTask(tasks, newTask);

            } else if (command.equals("event")) {

                // check for /from and /to
                if (!input[1].contains(" /from ") || !input[1].contains(" /to ")) {
                    throw new kxException("  ERROR! The description of a deadline must include /from and /to.");
                }

                String[] outputs = input[1].split(" /from ");
                // check for both task and event
                if (outputs.length != 2) {
                    throw new kxException("  ERROR! The description must include the event, start, and end timings. It cannot be empty.");
                }

                String[] outputs2 = outputs[1].split(" /to ");
                // check for both task and event
                if (outputs2.length != 2) {
                    throw new kxException("  ERROR! The description must include the start and end timings. It cannot be empty.");
                }
                Events newTask = new Events(outputs[0], outputs2[0], outputs2[1]);
                addTask(tasks, newTask);

            } else if (command.equals("delete")) {
                Task currTask = tasks.get(Integer.parseInt(input[1]) - 1);

                System.out.println("____________________________________________________________");
                System.out.println("  Noted. I've removed this task:\n  " + currTask.toString());

                tasks.remove(Integer.parseInt(input[1]) - 1);
                System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }
            else {
                throw new kxException("  ERROR! I'm sorry, but I am unable to handle that command yet :(");
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
