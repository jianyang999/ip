package lebron.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lebron.exception.LeBronException;
import lebron.task.RecurrenceInterval;
import lebron.task.TaskList;
import lebron.ui.Ui;

public class RecurringCommandTest {
    @Test
    public void execute_validArgs_taskAddedToListAndReportedToUi() throws LeBronException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Command command = new RecurringCommand("project meeting",
                LocalDateTime.of(2019, 10, 15, 18, 0), RecurrenceInterval.WEEKLY);

        String response = command.execute(taskList, new Ui());

        assertEquals(1, taskList.size());
        assertTrue(response.contains("[R][ ] project meeting (every week, next due: Oct 15 2019)"));
        assertTrue(response.contains("1 tasks left to grind now!"));
    }
}
