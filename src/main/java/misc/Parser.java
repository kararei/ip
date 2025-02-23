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

        return switch (command) {
            case "bye" -> ui.byeMessage();
            case "list" -> ui.listTaskMessage(taskList);
            case "mark" -> markCommand(input);
            case "unmark" -> unmarkCommand(input);
            case "deadline" -> deadlineCommand(input);
            case "todo" -> todoCommand(input);
            case "event" -> eventCommand(input);
            case "delete" -> deleteCommand(input);
            case "find" -> findCommand(input);
            default -> throw new kxException("  ERROR! I'm sorry, but I am unable to handle that command yet :(");
        };
    }

    private String markCommand(String[] input) {
        Task task = taskList.get(Integer.parseInt(input[1]) - 1);
        task.markAsDone();
        updateStorage();
        return ui.markMessage(task);
    }

    private String unmarkCommand(String[] input) {
        Task task = taskList.get(Integer.parseInt(input[1]) - 1);
        task.markAsUndone();
        updateStorage();
        return ui.unmarkMessage(task);
    }

    private String deadlineCommand(String[] input) throws kxException {
        if (!input[1].contains(" /by ")) {
            throw new kxException("  ERROR! The description of a deadline must include /by.");
        }
        String[] outputs = input[1].split(" /by ");

        if (outputs.length != 2) {
            throw new kxException("  ERROR! The description must include both the task and the deadline.");
        }

        Deadline newTask = new Deadline(outputs[0], outputs[1]);
        return addTask(newTask);
    }

    private String todoCommand(String[] input) {
        Todo newTask = new Todo(input[1]);
        return addTask(newTask);
    }

    private String eventCommand(String[] input) throws kxException {
        if (!input[1].contains(" /from ") || !input[1].contains(" /to ")) {
            throw new kxException("  ERROR! The description of a deadline must include /from and /to.");
        }

        String[] outputs = input[1].split(" /from ");
        if (outputs.length != 2) {
            throw new kxException("  ERROR! The description must include the event, start, and end timings." +
                    " It cannot be empty.");
        }

        String[] outputs2 = outputs[1].split(" /to ");
        if (outputs2.length != 2) {
            throw new kxException("  ERROR! The description must include the start and end timings." +
                    " It cannot be empty.");
        }
        Event newTask = new Event(outputs[0], outputs2[0], outputs2[1]);
        return addTask(newTask);
    }

    private String deleteCommand(String[] input) {
        Task currTask = taskList.get(Integer.parseInt(input[1]) - 1);
        taskList.remove(Integer.parseInt(input[1]) - 1);
        updateStorage();
        updateStorage();
        return ui.deleteMessage(taskList, currTask);
    }

    private String findCommand(String[] input) {
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

    private String addTask(Task newTask) {
        taskList.add(newTask);
        updateStorage();
        return ui.addTaskMessage(taskList, newTask);
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
