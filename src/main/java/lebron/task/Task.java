package lebron.task;

/**
 * Represents a Task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets Task to either be done or not done.
     *
     * @param status The boolean expression to be given to the Task.
     */
    public void setStatus(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns this Task's description.
     *
     * @return The task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Reformats Task to be saved in Storage.
     *
     * @return String reformatted.
     */
    public String reformat() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + this.description : "[ ] " + this.description;
    }
}
