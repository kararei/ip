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
    public void userCommand(String message) throws kxException {
        String[] input = message.split(" ", 2);
        String command = input[0];

        if (input.length == 1 && !command.equals("list") && !command.equals("bye")) {
            throw new kxException("  ERROR! The description of a task cannot be empty.");
        }

        switch (command) {
            case "bye":
                ui.byeMessage();
                break;

            case "list":
                ui.listTaskMessage(taskList);
                break;

            case "mark": {
                Task task = taskList.get(Integer.parseInt(input[1]) - 1);
                task.markAsDone();
                ui.markMessage(task);
                updateStorage();
                break;
            }

            case "unmark": {
                Task task = taskList.get(Integer.parseInt(input[1]) - 1);
                task.markAsUndone();
                ui.unmarkMessage(task);
                updateStorage();
                break;
            }

            case "deadline": {
                // check for /by
                if (!input[1].contains(" /by ")) {
                    throw new kxException("  ERROR! The description of a deadline must include /by.");
                }
                String[] outputs = input[1].split(" /by ");

                // Check for both task and deadline on input
                if (outputs.length != 2) {
                    throw new kxException("  ERROR! The description must include both the task and the deadline.");
                }

                Deadline newTask = new Deadline(outputs[0], outputs[1]);
                taskList.add(newTask);
                ui.addTaskMessage(taskList, newTask);
                updateStorage();
                break;
            }
            case "todo": {
                Todo newTask = new Todo(input[1]);
                taskList.add(newTask);
                ui.addTaskMessage(taskList, newTask);
                updateStorage();
                break;
            }
            case "event": {

                // check for /from and /to
                if (!input[1].contains(" /from ") || !input[1].contains(" /to ")) {
                    throw new kxException("  ERROR! The description of a deadline must include /from and /to.");
                }

                String[] outputs = input[1].split(" /from ");
                // check for both task and event
                if (outputs.length != 2) {
                    throw new kxException("  ERROR! The description must include the event, start, and end timings." +
                            " It cannot be empty.");
                }

                String[] outputs2 = outputs[1].split(" /to ");
                // check for both task and event
                if (outputs2.length != 2) {
                    throw new kxException("  ERROR! The description must include the start and end timings." +
                            " It cannot be empty.");
                }
                Event newTask = new Event(outputs[0], outputs2[0], outputs2[1]);
                taskList.add(newTask);
                ui.addTaskMessage(taskList, newTask);
                updateStorage();
                break;
            }
            case "delete":
                Task currTask = taskList.get(Integer.parseInt(input[1]) - 1);
                taskList.remove(Integer.parseInt(input[1]) - 1);
                ui.deleteMessage(taskList, currTask);
                updateStorage();
                break;

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
