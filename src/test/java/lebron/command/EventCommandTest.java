package lebron.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lebron.exception.LeBronException;
import lebron.task.TaskList;
import lebron.ui.StubUi;

public class EventCommandTest {
    @Test
    public void execute_validDescriptionAndDates_taskAddedToListAndReportedToUi() throws LeBronException {
        TaskList taskList = new TaskList(new ArrayList<>());
        StubUi ui = new StubUi();
        Command command = new EventCommand("project meeting",
                LocalDateTime.of(2019, 10, 16, 9, 0), LocalDateTime.of(2019, 10, 16, 11, 0));

        command.execute(taskList, ui);

        assertEquals(1, taskList.size());
        assertEquals("[E][ ] project meeting (from Oct 16 2019 til Oct 16 2019)", ui.lastEventAdded.toString());
        assertEquals(1, ui.lastEventAddedSize);
    }
}
