package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Event(String description, String from, String to) {
        super(description);

        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Error in Input format: Input should follow dd-MM-yyyy HHmm format ");
        }

        try {
            this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Error in Input format: Input should follow dd-MM-yyyy HHmm format ");
        }
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMATTER) + " to: "
                + to.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String toFileFormat() {
        return String.format("E | %d | %s | %s | %s\n", isDone ? 1 : 0, description, from.format(INPUT_FORMATTER),
                to.format(INPUT_FORMATTER));
    }
}
