import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task{

    protected LocalDateTime by;
    private static final DateTimeFormatter input_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private static final DateTimeFormatter output_formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private static final DateTimeFormatter file_formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    public Deadlines(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, input_formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Error in Input format: Input should follow dd-MM-yyyy HHmm format ");
        }
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(output_formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        String output = String.format("D | %d | %s | %s\n", isDone ? 1 : 0, description, by.format(input_formatter));
        return output;
    }
}
