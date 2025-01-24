public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String markAsDone() {
        this.isDone = true;
        return " Nice! I've marked this task as done:\n" + this.toString();
    }


    public String markAsUndone() {
        this.isDone = false;
        return "  OK, I've marked this task as not done yet:\n" + this.toString();
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}
