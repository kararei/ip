public class Events extends Task {

    protected String from;
    protected String to;

    public Events(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
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
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileFormat() {
        String output = String.format("E | %d | %s | %s-%s\n", isDone ? 1 : 0, description, from, to);
        return output;
    }
}
