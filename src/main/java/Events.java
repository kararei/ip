import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter input_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter output_formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter file_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public Events(String description, String from, String to) {
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

    public Events(String description, String timing) {
        super(description);

    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(output_formatter) + " to: " + to.format(output_formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        String output = String.format("E | %d | %s | %s | %s\n", isDone ? 1 : 0, description, from.format(input_formatter), to.format(input_formatter));
        return output;
    }
}
