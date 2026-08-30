package lebron.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event, a Task that occurs over a start and end date/time.
 */
public class Event extends Task {
    private static final DateTimeFormatter DISPLAY_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructs an Event with the given description, start, and end date/time.
     *
     * @param description Task description.
     * @param start The date/time the event starts.
     * @param end The date/time the event ends.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String reformat() {
        return "E | " + super.reformat() + " | " + this.start + " | " + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + this.start.format(DISPLAY_DATE_FORMAT)
                + " til " + this.end.format(DISPLAY_DATE_FORMAT) + ")";
    }
}
