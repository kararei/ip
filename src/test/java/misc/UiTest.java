package misc;
import task.Task;
import task.Todo;


import java.util.ArrayList;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class UiTest {

    @Test
    public void testUi_helloMessage() {
        Ui ui = new Ui();

        String helloMessage = "  Hello! I'm Kx, the kai xin bot!\nWhat can I do for you?";
        assertEquals(helloMessage, ui.helloMessage());
    }

    @Test
    public void testUi_addTaskMessage() {
        Ui ui = new Ui();

        ArrayList<Task> taskList = new ArrayList<>();
        Task task = new Todo("todolist");
        taskList.add(task);

        String addTaskMessage = "Got it. I've added this task:\n"
                + task.toString() + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
        assertEquals(addTaskMessage, ui.addTaskMessage(taskList, task));
    }

    @Test
    public void  testUi_errorMessage() {
        Ui ui = new Ui();
        String errorMessage = "ERROR!! \n" + "This is a Ui Test Error Message";
        assertEquals(errorMessage, ui.errorMessage("This is a Ui Test Error Message"));
    }

}
