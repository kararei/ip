package task;

/**
 * Represents a Todo task with a description that extends the general Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task.
     * @param description is The description of the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Obtain type of task.
     * @return A string representing the task type "T" for Todo.
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * Returns a string representation of Todo task.
     * @return A formatted string of Todo
     */
    @Override
    public String toString() {
        assert description != null : "Description should not be null when converting to string output";
        return "[T]" + super.toString();
    }

    /**
     * Convert to a string representation of Todo task that can be stored in Storage class.
     * @return A formatted string of Todo
     */
    @Override
    public String toFileFormat() {
        assert description != null : "Description should not be null before saving to file";
        return String.format("T | %d | %s\n", isDone ? 1 : 0, description);
    }
}
