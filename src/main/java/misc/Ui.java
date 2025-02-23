package misc;

import task.Task;
import java.util.ArrayList;

/**
 * Handles user interaction for the messages that are shown on the console.
 */
public class Ui {

    /**
     * Shows welcome/hello message when the program begins.
     */
    public String helloMessage() {
        return "  Hello! I'm Kx, the kai xin bot!\nWhat can I do for you?";
    }

    /**
     * Shows goodbye message when the program ends.
     */
    public String byeMessage() {
        return "Bye bye and hope to see you again soon!";
    }

    /**
     * Shows an error message.
     *
     * @param message The error message to be concatenated and shown.
     */
    public String errorMessage(String message) {
        return "ERROR!! \n" + message;
    }

    /**
     * Shows a task is added message.
     *
     * @param taskList The list of tasks.
     * @param newTask  The new task added
     */
    public String addTaskMessage(ArrayList<Task> taskList, Task newTask) {
        return "Got it. I've added this task:\n" + newTask.toString() +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Shows the tasks in the task list.
     *
     * @param taskList The list of tasks.
     */
    public String listTaskMessage(ArrayList<Task> taskList) {
        String header = "Here are the tasks in your list:\n";
        return listing(header, taskList);
    }

    /**
     * Shows task is deleted message.
     *
     * @param taskList The list of tasks.
     * @param currTask The removed task.
     */
    public String deleteMessage(ArrayList<Task> taskList, Task currTask) {
        return "Noted. I've removed this task:\n" + currTask.toString() +
                "\nNow you have " + (taskList.size()) + " tasks in the list.";
    }

    /**
     * Shows task is marked as done message.
     *
     * @param task The task that gets marked as done.
     */
    public String markMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Shows task is marked as not done message.
     *
     * @param task The task that gets unmarked.
     */
    public String unmarkMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Shows the list of matching tasks.
     *
     * @param matchingTaskList The list of matching tasks.
     * @return Formatted message displaying the matching tasks.
     */
    public String findMessage(ArrayList<Task> matchingTaskList) {
        String header = "Here are the matching tasks in your list:\n";
        if (matchingTaskList.isEmpty()) {
            return header + "No matching tasks found.";
        }
        return listing(header, matchingTaskList);

    }

    /**
     * Formats a list of tasks into a structured string message.
     */
    private String listing(String header, ArrayList<Task> taskList) {
        StringBuilder output = new StringBuilder(header);
        for (int i = 0; i < taskList.size(); i++) {
            Task curr = taskList.get(i);
            output.append(i + 1).append(". ").append(curr).append("\n");
        }
        return output.toString();
    }
}
