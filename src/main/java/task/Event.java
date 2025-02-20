package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Event task with a description and time range that extends the general Task class.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter input_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter output_formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter file_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    /**
     * Constructs a new Event task.
     * @param description The description of the Event task.
     * @param from The start time of the Event in "dd-MM-yyyy HHmm" format.
     * @param to The end time of the Event in "dd-MM-yyyy HHmm" format.
     */
    public Event(String description, String from, String to) {
        super(description);

        try {
            this.from = LocalDateTime.parse(from, input_formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error in Input format: Input should follow dd-MM-yyyy HHmm format ");
        }

        try {
            this.to = LocalDateTime.parse(to, input_formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error in Input format: Input should follow dd-MM-yyyy HHmm format ");
        }
    }
    /**
     * Obtain type of task.
     * @return A string representing the task type "E" for Event.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Returns a string representation of the Event task.
     * @return A formatted string of Event with its description and time range denoted by 'to' and 'from'.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(output_formatter) + " to: " + to.format(output_formatter) + ")";
    }

    /**
     * Convert to a string representation of Event task that can be stored in Storage class.
     * @return A formatted string of Event
     */
    @Override
    public String toFileFormat() {
        String output = String.format("E | %d | %s | %s | %s\n", isDone ? 1 : 0, description, from.format(input_formatter), to.format(input_formatter));
        return output;
    }
}
