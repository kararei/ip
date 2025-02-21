package task;

/**
 * Represents a general task with a description and completion or "done" status.
 * An abstract class for the various specific task types.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status icon of the task.
     * @return "X" if the task is done, if not a whitespace.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Obtains the type of task.
     * @return A string representing the type of the task.
     */
    public abstract String getTaskType();

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not completed.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * @return A formatted string displaying the task's status and description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts the task into a formatted string suitable for Storage class.
     * @return A formatted string representing the task for a file
     */
    public abstract String toFileFormat();
}
