package task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileFormat() {
        String output = String.format("T | %d | %s\n", isDone ? 1 : 0, description);
        return output;
    }
}
