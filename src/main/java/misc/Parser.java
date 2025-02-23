package misc;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles user commands parsing and executes corresponding actions.
 */
public class Parser {

    private Ui ui;
    private ArrayList<Task> taskList;

    /**
     * Constructs a Parser object.
     * @param ui The user interactions object for this Parse.
     * @param taskList The list of tasks.
     */
    public Parser(Ui ui, ArrayList<Task> taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Parses user input and executes the corresponding command.
     * @param message The input command from the user.
     * @throws kxException If the command is invalid or missing necessary details.
     */
    public String userCommand(String message) throws kxException {
        String[] input = message.split(" ", 2);
        String command = input[0];

        if (input.length == 1 && !command.equals("list") && !command.equals("bye")) {
            throw new kxException("  ERROR! The description of a task cannot be empty.");
        }

        switch (command) {
            case "bye":
                return ui.byeMessage();

            case "list":
                return ui.listTaskMessage(taskList);

            case "mark": {
                int index = Integer.parseInt(input[1]) -1;
                assert index >= 0 && index < taskList.size() : "Invalid task index: " + index;

                Task task = taskList.get(index);
                task.markAsDone();
                updateStorage();
                return ui.markMessage(task);
            }

            case "unmark": {
                int index = Integer.parseInt(input[1]) -1;
                assert index >= 0 && index < taskList.size() : "Invalid task index: " + index;

                Task task = taskList.get(index);
                task.markAsUndone();
                updateStorage();
                return ui.unmarkMessage(task);
            }

            case "deadline": {
                // check for /by
                if (!input[1].contains(" /by ")) {
                    throw new kxException("  ERROR! The description of a deadline must include /by.");
                }
                String[] outputs = input[1].split(" /by ");
                assert outputs.length == 2 : "Deadline task should have exactly two parts: " +
                        "The task description and the deadline.";

                Deadline newTask = new Deadline(outputs[0], outputs[1]);
                taskList.add(newTask);
                updateStorage();
                return ui.addTaskMessage(taskList, newTask);
            }
            case "todo": {
                Todo newTask = new Todo(input[1]);
                taskList.add(newTask);
                updateStorage();
                return ui.addTaskMessage(taskList, newTask);
            }
            case "event": {

                if (!input[1].contains(" /from ") || !input[1].contains(" /to ")) {
                    throw new kxException("  ERROR! The description of a deadline must include /from and /to.");
                }

                String[] outputs = input[1].split(" /from ");
                assert outputs.length == 2 : "Event task description must contain the event, start and end timings.";

                String[] outputs2 = outputs[1].split(" /to ");
                assert outputs2.length == 2 : "Event task description must contain the event, start and end timings.";
                // check for both task and event

                Event newTask = new Event(outputs[0], outputs2[0], outputs2[1]);
                taskList.add(newTask);
                updateStorage();
                return ui.addTaskMessage(taskList, newTask);
            }
            case "delete": {
                int index = Integer.parseInt(input[1]) -1;
                assert index >= 0 && index < taskList.size() : "Invalid task index: " + index;

                Task currTask = taskList.get(index);
                taskList.remove(index);
                updateStorage();
                return ui.deleteMessage(taskList, currTask);
            }
            case "find": {
                String keyword = input[1];
                ArrayList<Task> matchingTaskList = new ArrayList<>();
                for (Task task : taskList) {
                    String description = task.getDescription();
                    if (description.contains(keyword)) {
                        matchingTaskList.add(task);
                    }
                }
                return ui.findMessage(matchingTaskList);
            }
            default:
                throw new kxException("  ERROR! I'm sorry, but I am unable to handle that command yet :(");
        }
    }

    /**
     * Updates the storage file with new task list.
     */
    private void updateStorage() {
        try {
            Storage.updateFile(taskList);
        } catch (IOException e) {
            ui.errorMessage("Error in updating storage: " + e.getMessage());
        }
    }
}
