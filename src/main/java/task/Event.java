package task;

import misc.kxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Event task with a description and time range that extends the general Task class.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs a new Event task.
     * @param description The description of the Event task.
     * @param from The start time of the Event in "dd-MM-yyyy HHmm" format.
     * @param to The end time of the Event in "dd-MM-yyyy HHmm" format.
     */
    public Event(String description, String from, String to) throws kxException {
        super(description);

        try {
            assert this.from == null: "Parsed DateTime object 'from' should not be null";
            this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new kxException("Error in Input format: Input after /from should follow dd-MM-yyyy HHmm format");
        }

        try {
            assert this.to == null: "Parsed DateTime object 'to' should not be null";
            this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new kxException("Error in Input format: Input after /to should follow dd-MM-yyyy HHmm format");
        }
    }

    public boolean occursOn(LocalDate selectedDate) {
        LocalDate fromLD = from.toLocalDate();
        LocalDate toLD = to.toLocalDate();
        return !selectedDate.isBefore(fromLD) && !selectedDate.isAfter(toLD);
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
        assert description != null : "Description should not be null when converting to string output";
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMATTER) + " to: "
                + to.format(OUTPUT_FORMATTER) + ")";
    }

    /**
     * Convert to a string representation of Event task that can be stored in Storage class.
     * @return A formatted string of Event
     */
    @Override
    public String toFileFormat() {
        assert description != null : "Description should not be null before saving to file";
        return String.format("E | %d | %s | %s | %s\n", isDone ? 1 : 0, description, from.format(INPUT_FORMATTER),
                to.format(INPUT_FORMATTER));
    }
}
