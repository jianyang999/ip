package lebron.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline, a Task that must be completed by a specific date/time.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a Deadline with the given description and due date/time.
     *
     * @param description Task description.
     * @param deadline The date/time by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String reformat() {
        return "D | " + super.reformat() + " | " + this.deadline;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
