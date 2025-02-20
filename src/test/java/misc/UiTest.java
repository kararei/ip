package misc;
import task.Task;
import task.Todo;


import java.util.ArrayList;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class UiTest {

    @Test
    public void testUi_helloMessage() {
        Ui ui = new Ui();
        // To verify and redirect output to be printed
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        String helloMessage = "  Hello! I'm kx, the kai xin bot!\n  What can I do for you?";

        ui.helloMessage();
        String output = outputStream.toString();
        assertTrue(output.contains(helloMessage));

    }

    @Test
    public void testUi_addTaskMessage() {
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ArrayList<Task> taskList = new ArrayList<>();
        Task task = new Todo("todolist");
        taskList.add(task);
        ui.addTaskMessage(taskList, task);
        String output = outputStream.toString();

        String addTaskMessage = Ui.SEPARATOR + "\n"
                + "  Got it. I've added this task:\n"
                + "  " + task.toString() + "\n"
                + "  Now you have " + taskList.size() + " tasks in the list.\n"
                + Ui.SEPARATOR + "\n";
        assertTrue(output.contains(addTaskMessage));
    }

    @Test
    public void  testUi_errorMessage() {
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ui.errorMessage("This is a Ui Test Error Message");
        String output = outputStream.toString();

        String errorMessage = "  ERROR!! \n" + "This is a Ui Test Error Message";
        assertTrue(output.contains(errorMessage));
    }

}
