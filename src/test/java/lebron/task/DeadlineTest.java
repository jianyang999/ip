package lebron.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private final LocalDateTime by = LocalDateTime.of(2019, 10, 15, 18, 0);

    @Test
    public void toString_notDone_correctFormatWithHumanReadableDate() {
        Deadline deadline = new Deadline("return book", by);
        assertEquals("[D][ ] return book (by: Oct 15 2019)", deadline.toString());
    }

    @Test
    public void toString_done_correctFormat() {
        Deadline deadline = new Deadline("return book", by);
        deadline.setStatus(true);
        assertEquals("[D][X] return book (by: Oct 15 2019)", deadline.toString());
    }

    @Test
    public void reformat_notDone_correctSaveFormatWithIsoDate() {
        Deadline deadline = new Deadline("return book", by);
        assertEquals("D | 0 | return book | 2019-10-15T18:00", deadline.reformat());
    }
}
