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
        return kx.SEPARATOR + "\n  Nice! I've marked this task as done:\n  " + this.toString() + "\n" + kx.SEPARATOR;
    }


    public String markAsUndone() {
        this.isDone = false;
        return kx.SEPARATOR + "\n  OK, I've marked this task as not done yet:\n  " + this.toString() + "\n" + kx.SEPARATOR;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}
