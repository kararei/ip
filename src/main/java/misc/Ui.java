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
    public String helloMessage() {
        return "  Hello! I'm kx, the kai xin bot!\nWhat can I do for you?";
    }

    /**
     * Shows goodbye message when the program ends.
     */
    public String byeMessage() {
        return "Bye bye and hope to see you again soon!";
    }

    /**
     * Shows an error message.
     * @param message The error message to be concatenated and shown.
     */
    public String errorMessage(String message) {
        return "ERROR!! \n" + message;
    }

    /**
     * Shows a task is added message.
     * @param taskList The list of tasks.
     * @param newTask The new task added
     */
    public String addTaskMessage(ArrayList<Task> taskList, Task newTask) {
        return "Got it. I've added this task:\n" + newTask.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Shows the tasks in the task list.
     * @param taskList The list of tasks.
     */
    public String listTaskMessage(ArrayList<Task> taskList) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.get(i);
            output.append(i + 1).append(". ").append(curr.toString()).append("\n");
        }
        return output.toString();
    }

    /**
     * Shows task is deleted message.
     * @param taskList The list of tasks.
     * @param currTask The removed task.
     */
    public String deleteMessage (ArrayList<Task> taskList, Task currTask) {
        return "Noted. I've removed this task:\n" + currTask.toString() +
                "\nNow you have " + (taskList.size()) + " tasks in the list.";
    }

    /**
     * Shows task is marked as done message.
     * @param task The task that gets marked as done.
     */
    public String markMessage (Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Shows task is marked as not done message.
     * @param task The task that gets unmarked.
     */
    public String unmarkMessage (Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    public String findMessage(ArrayList<Task> matchingTaskList) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        if (matchingTaskList.isEmpty()) {
            System.out.println("  No matching tasks found.");
        } else {
            for (int i = 0; i < matchingTaskList.size(); i++) {
                Task curr = matchingTaskList.get(i);
                output.append(i + 1).append(". ").append(curr.toString()).append("\n");
            }
        }
        return output.toString();
    }
}
