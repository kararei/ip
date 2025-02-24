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

    private String markCommand(String[] input) throws kxException {
        int index = Integer.parseInt(input[1]) -1;

        if (index < 0 || index >= taskList.size()) {
            throw new kxException("Invalid task index: " + (index + 1) +
                    "\nindex should be between 1 and the total number of tasks");
        }

        Task task = taskList.get(index);
        task.markAsDone();
        updateStorage();
        return ui.markMessage(task);
    }

    private String unmarkCommand(String[] input) throws kxException {
        int index = Integer.parseInt(input[1]) -1;

        if (index < 0 || index >= taskList.size()) {
            throw new kxException("Invalid task index: " + (index + 1) +
                    "\nindex should be between 1 and the total number of tasks");
        }

        Task task = taskList.get(index);
        task.markAsUndone();
        updateStorage();
        return ui.unmarkMessage(task);
    }

    private String deadlineCommand(String[] input) throws kxException {
        if (!input[1].contains(" /by ")) {
            throw new kxException("  ERROR! The description of a deadline must include /by.");
        }
        String[] outputs = input[1].split(" /by ");
        assert outputs.length == 2 : "Deadline task should have exactly two parts: " +
                "The task description and the deadline.";

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
        assert outputs.length == 2 : "Event task description must contain the event, start and end timings.";

        String[] outputs2 = outputs[1].split(" /to ");
        assert outputs2.length == 2 : "Event task description must contain the event, start and end timings.";

        Event newTask = new Event(outputs[0], outputs2[0], outputs2[1]);
        return addTask(newTask);
    }

    private String deleteCommand(String[] input) {
        int index = Integer.parseInt(input[1]) -1;
        assert index >= 0 && index < taskList.size() : "Invalid task index: " + index;

        Task currTask = taskList.get(index);
        taskList.remove(index);
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
