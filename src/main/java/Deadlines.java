public class Deadlines extends Task{

    protected String by;

    public Deadlines(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        String output = String.format("D | %d | %s | %s\n", isDone ? 1 : 0, description, by);
        return output;
    }
}
