package lebron.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lebron.exception.LeBronException;
import lebron.task.TaskList;
import lebron.ui.StubUi;

public class DeadlineCommandTest {
    @Test
    public void execute_validDescriptionAndDate_taskAddedToListAndReportedToUi() throws LeBronException {
        TaskList taskList = new TaskList(new ArrayList<>());
        StubUi ui = new StubUi();
        Command command = new DeadlineCommand("return book", LocalDateTime.of(2019, 10, 15, 18, 0));

        command.execute(taskList, ui);

        assertEquals(1, taskList.size());
        assertEquals("[D][ ] return book (by: Oct 15 2019)", ui.lastDeadlineAdded.toString());
        assertEquals(1, ui.lastDeadlineAddedSize);
    }
}
