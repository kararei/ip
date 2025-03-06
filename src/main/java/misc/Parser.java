package misc;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.io.IOException;
import java.time.LocalDateTime;
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
            case "view" -> viewCommand(input);
            default -> throw new kxException("  ERROR! I'm sorry, but I am unable to handle that command yet :(");
        };
    }

    /**
     * Marks a task as done.
     * @param input The user input gets split into command and arguments.
     * @return Message confirming task was marked as done.
     * @throws kxException If the task index is invalid.
     */
    private String markCommand(String[] input) throws kxException {
        int index;
        try {
            index = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException e) {
            throw new kxException(input[1] + " is not a integer.\nPlease provide a valid task index.");
        }

        if (index < 0 || index >= taskList.size()) {
            throw new kxException("Invalid task index: " + (index + 1) +
                    "\nindex should be between 1 and the total number of tasks");
        }
        Task task = taskList.get(index);
        task.markAsDone();
        updateStorage();
        return ui.markMessage(task);
    }

    /**
     * Unmarks a task as not done.
     * @param input The user input gets split into command and arguments.
     * @return Mssage confirming task was unmarked.
     * @throws kxException If the task index is invalid.
     */
    private String unmarkCommand(String[] input) throws kxException {
        int index;
        try {
            index = Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException e) {
            throw new kxException(input[1] + " is not a integer.\nPlease provide a valid task index.");
        }
        if (index < 0 || index >= taskList.size()) {
            throw new kxException("Invalid task index: " + (index + 1) +
                    "\nindex should be between 1 and the total number of tasks");
        }

        Task task = taskList.get(index);
        task.markAsUndone();
        updateStorage();
        return ui.unmarkMessage(task);
    }

    /**
     * Adds a new deadline task.
     * @param input The user input split into command and arguments.
     * @return The response message confirming the task was added.
     * @throws kxException If the input format is incorrect.
     */
    private String deadlineCommand(String[] input) throws kxException {
        if (!input[1].contains(" /by ")) {
            throw new kxException("  ERROR! The description of a deadline must include /by and the date after.");
        }
        String[] outputs = input[1].split(" /by ", -1);
        System.out.println(outputs[0]);
        System.out.println(outputs[1]);
        try {
            assert outputs.length == 2 : "Deadline task should have exactly two parts: " +
                    "The task description and the deadline.";
        } catch (AssertionError e) {
            throw new kxException("Deadline task should have exactly two parts: " +
                    "The task description and the deadline."); // Re-throw the exception if needed
        }

        Deadline newTask = new Deadline(outputs[0], outputs[1]);
        return addTask(newTask);
    }

    /**
     * Adds a new todo task.
     * @param input The user input split into command and arguments.
     * @return The response message confirming the task was added.
     */
    private String todoCommand(String[] input) {
        Todo newTask = new Todo(input[1]);
        return addTask(newTask);
    }

    /**
     * Adds a new event task.
     * @param input The user input split into command and arguments.
     * @return The response message confirming the task was added.
     * @throws kxException If the input format is incorrect.
     */
    private String eventCommand(String[] input) throws kxException {
        if (!input[1].contains(" /from ") || !input[1].contains(" /to ")) {
            throw new kxException("  ERROR! The description of a deadline must include /from and /to.");
        }

        String[] outputs = input[1].split(" /from ");
        assert outputs.length == 2 : "Event task description must contain the event, start and end timings.";

        String[] outputs2 = outputs[1].split(" /to ");
        assert outputs2.length == 2 : "Event task description must contain the event, start and end timings.";

        Event newTask = new Event(outputs[0], outputs2[0], outputs2[1]);
        return addTask(newTask);
    }
    /**
     * Deletes a task from the list.
     * @param input The user input is plit into command and arguments.
     * @return The response message confirming the task was deleted.
     */
    private String deleteCommand(String[] input) {
        int index = Integer.parseInt(input[1]) -1;
        assert index >= 0 && index < taskList.size() : "Invalid task index: " + index;

        Task currTask = taskList.get(index);
        taskList.remove(index);
        updateStorage();
        return ui.deleteMessage(taskList, currTask);
    }

    /**
     * Searches for tasks matching a keyword.
     * @param input The user input split into command and arguments.
     * @return A list of matching tasks.
     */
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

    /**
            * Displays tasks scheduled for a specific date.
     * @param input The user input split into command and arguments.
     * @return A formatted string containing tasks scheduled for the given date.
     * @throws kxException If the date format is incorrect or missing.
     */
    private String viewCommand(String[] input) throws kxException {
        if (input.length < 2) {
            throw new kxException("Please specify a date in yyyy-MM-dd format.");
        }
        LocalDate selectedDate;
        try {
            selectedDate = LocalDate.parse(input[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new kxException("Invalid date format, date must be in use yyyy-MM-dd.");
        }
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (Task task : taskList) {
            if (task instanceof Deadline && ((Deadline) task).getBy().isEqual(selectedDate)) {
                tasksOnDate.add(task);
            } else if (task instanceof Event && ((Event) task).occursOn(selectedDate)) {
                tasksOnDate.add(task);
            }
        }
        if (tasksOnDate.isEmpty()) {
            return "No tasks scheduled for " + selectedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        StringBuilder output = new StringBuilder("Tasks scheduled for " +
                selectedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":\n");
        for (int i = 0; i < tasksOnDate.size(); i++) {
            output.append(i + 1).append(". ").append(tasksOnDate.get(i)).append("\n");
        }
        return output.toString();
    }

    /**
     * Adds a new task to the task list and updates storage.
     * @param newTask The task to be added.
     * @return A response message confirming the addition of the task.
     */
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
