package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task with a description and a due date that extends the General Task class.
 */
public class Deadlines extends Task {

    protected LocalDateTime by;
    private static final DateTimeFormatter input_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter output_formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter file_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Constructs a new Deadline task.
     * @param description The description of the Deadline task.
     * @param by The due date and time of the Deadline in "dd-MM-yyyy HHmm" format.
     */
    public Deadlines(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, input_formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error in Input format: Input should follow dd-MM-yyyy HHmm format ");
        }
    }

    /**
     * Obtain type of task.
     * @return A string representing the task type "D" for Deadline.
     */
    @Override
    public String getTaskType() {
        return "D";
    }

    /**
     * Returns a string representation of the Deadline task.
     * @return A formatted string of Deadline with its description and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(output_formatter) + ")";
    }

    /**
     * Convert to a string representation of Deadline task that can be stored in Storage class.
     * @return A formatted string of Deadline.
     */
    @Override
    public String toFileFormat() {
        String output = String.format("D | %d | %s | %s\n", isDone ? 1 : 0, description, by.format(input_formatter));
        return output;
    }
}
