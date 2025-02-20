package misc;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    private Ui ui;
    private ArrayList<Task> taskList;


    public Parser(Ui ui, ArrayList<Task> taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

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

                //Update changes
                try {
                    Storage.updateFile(taskList);
                } catch (IOException e) {
                    System.out.println("Error in updating misc.Storage: " + e.getMessage());
                }

                break;
            }
            case "unmark": {
                Task task = taskList.get(Integer.parseInt(input[1]) - 1);
                task.markAsUndone();
                ui.unmarkMessage(task);

                // Update changes
                try {
                    Storage.updateFile(taskList);
                } catch (IOException e) {
                    System.out.println("Error in updating misc.Storage: " + e.getMessage());
                }

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
                // Update changes
                try {
                    Storage.updateFile(taskList);
                } catch (IOException e) {
                    ui.errorMessage(e.getMessage());
                }


                break;
            }
            case "todo": {
                Todo newTask = new Todo(input[1]);
                taskList.add(newTask);
                ui.addTaskMessage(taskList, newTask);

                // Update changes
                try {
                    Storage.updateFile(taskList);
                } catch (IOException e) {
                    ui.errorMessage(e.getMessage());
                }

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

                // Update changes
                try {
                    Storage.updateFile(taskList);
                } catch (IOException e) {
                    ui.errorMessage(e.getMessage());
                }

                break;
            }
            case "delete":
                Task currTask = taskList.get(Integer.parseInt(input[1]) - 1);
                taskList.remove(Integer.parseInt(input[1]) - 1);
                ui.deleteMessage(taskList, currTask);

                // Update changes
                try {
                    Storage.updateFile(taskList);
                } catch (IOException e) {
                    System.out.println("Error in updating misc.Storage: " + e.getMessage());
                }
                break;
            default:
                throw new kxException("  ERROR! I'm sorry, but I am unable to handle that command yet :(");
        }
    }

}
