package lebron.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void toString_newTask_notDoneFormat() {
        Task task = new Task("read book");
        assertEquals("[ ] read book", task.toString());
    }

    @Test
    public void toString_afterSetStatusTrue_doneFormat() {
        Task task = new Task("read book");
        task.setStatus(true);
        assertEquals("[X] read book", task.toString());
    }

    @Test
    public void toString_afterSetStatusTrueThenFalse_notDoneFormat() {
        Task task = new Task("read book");
        task.setStatus(true);
        task.setStatus(false);
        assertEquals("[ ] read book", task.toString());
    }

    @Test
    public void reformat_notDone_correctSaveFormat() {
        Task task = new Task("read book");
        assertEquals("0 | read book", task.reformat());
    }

    @Test
    public void reformat_done_correctSaveFormat() {
        Task task = new Task("read book");
        task.setStatus(true);
        assertEquals("1 | read book", task.reformat());
    }
}
