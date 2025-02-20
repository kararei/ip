package misc;
import task.Task;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

public class ParserTest {
    @Test
    public void testParser_splitTodoInput() {
        String input = "todo sleep more";
        String[] result = input.split(" ", 2);
        assertEquals("todo", result[0]);
        assertEquals("sleep more", result[1]);
    }

    @Test
    public void testParser_emptyDeadLinesInput() {
        ArrayList<Task> taskList = new ArrayList<>();
        Parser parser = new Parser(new Ui(), taskList);
        Exception exception = assertThrows(kxException.class, () -> {
            parser.userCommand("deadline");
        });

        assertEquals("  ERROR! The description of a task cannot be empty.", exception.getMessage());
    }

    @Test
    public void testParser_validEventsInput() {
        String input = "event meeting /from 12-12-2025 1400 /to 12-12-2025 1600";
        String[] parts = input.split(" ", 2);
        assertEquals("event", parts[0]);

        String[] eventSplit = parts[1].split(" /from ");
        assertEquals("meeting", eventSplit[0]);

        String[] timeSplit = eventSplit[1].split(" /to ");
        assertEquals("12-12-2025 1400", timeSplit[0]);
        assertEquals("12-12-2025 1600", timeSplit[1]);
    }

}
