package misc;

import task.Task;

import java.util.ArrayList;

/**
 * Handles user interaction for the messages that are shown on the console.
 */
public class Ui {
    public static final String SEPARATOR = "____________________________________________________________";

    /**
     * Shows welcome/hello message when the program begins.
     */
    public void helloMessage() {
        System.out.println(SEPARATOR);
        System.out.println("  Hello! I'm kx, the kai xin bot!\n  What can I do for you?");
        System.out.println(SEPARATOR);
    }

    /**
     * Shows goodbye message when the program ends.
     */
    public void byeMessage() {
        System.out.println(SEPARATOR);
        System.out.println("  Bye bye and hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    /**
     * Shows an error message.
     * @param message The error message to be concatenated and shown.
     */
    public void errorMessage(String message) {
        System.out.println("  ERROR!! \n" + message);
    }

    /**
     * Shows a task is added message.
     * @param taskList The list of tasks.
     * @param newTask The new task added
     */
    public void addTaskMessage(ArrayList<Task> taskList, Task newTask) {
        System.out.println(SEPARATOR);
        System.out.println("  Got it. I've added this task:");
        System.out.println("  " + newTask.toString());
        System.out.println("  Now you have " + taskList.size() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Shows the tasks in the task list.
     * @param taskList The list of tasks.
     */
    public void listTaskMessage(ArrayList<Task> taskList) {
        System.out.println(SEPARATOR);
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.get(i);
            System.out.println("  " + (i + 1) + ". " + curr.toString());
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Shows task is deleted message.
     * @param taskList The list of tasks.
     * @param currTask The removed task.
     */
    public void deleteMessage (ArrayList<Task> taskList, Task currTask) {
        System.out.println(SEPARATOR);
        System.out.println("  Noted. I've removed this task:\n  " + currTask.toString());
        System.out.println("  Now you have " + (taskList.size()) + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    /**
     * Shows task is marked as done message.
     * @param task The task that gets marked as done.
     */
    public void markMessage (Task task) {
        System.out.println(SEPARATOR);
        System.out.println("  Nice! I've marked this task as done:\n  " + task.toString());
        System.out.println(SEPARATOR);
    }

    /**
     * Shows task is marked as not done message.
     * @param task The task that gets unmarked.
     */
    public void unmarkMessage (Task task) {
        System.out.println(SEPARATOR);
        System.out.println("  OK, I've marked this task as not done yet:\n  " + task.toString());
        System.out.println(SEPARATOR);
    }



}
