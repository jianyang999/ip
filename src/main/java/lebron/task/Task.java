package lebron.task;

import java.util.Objects;

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
        assert description != null && !description.isBlank()
                : "Task description should not be null or blank; Parser should have rejected it already";
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

    /**
     * Two Tasks are equal if they are the exact same concrete type and have the same
     * description. Subclasses with extra fields (e.g. dates) should also compare those,
     * by combining this check with their own via {@code super.equals(obj)}. This is used
     * by TaskList to reject adding a task that duplicates one already in the list.
     *
     * @param obj The object to compare against.
     * @return true if obj is a Task of the same concrete type with the same description.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task other = (Task) obj;
        return description.equals(other.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), description);
    }
}
