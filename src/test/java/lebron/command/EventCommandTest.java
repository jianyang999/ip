package lebron.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lebron.exception.LeBronException;
import lebron.task.TaskList;
import lebron.ui.Ui;

public class EventCommandTest {
    @Test
    public void execute_validDescriptionAndDates_taskAddedToListAndReportedToUi() throws LeBronException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Command command = new EventCommand("project meeting",
                LocalDateTime.of(2019, 10, 16, 9, 0), LocalDateTime.of(2019, 10, 16, 11, 0));

        String response = command.execute(taskList, new Ui());

        assertEquals(1, taskList.size());
        assertTrue(response.contains("[E][ ] project meeting (from Oct 16 2019 til Oct 16 2019)"));
        assertTrue(response.contains("1 tasks left to grind now!"));
    }
}
