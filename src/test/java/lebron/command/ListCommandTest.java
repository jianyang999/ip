package lebron.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lebron.task.TaskList;
import lebron.ui.StubUi;

public class ListCommandTest {
    @Test
    public void execute_reportsTaskListToUi() {
        TaskList taskList = new TaskList(new ArrayList<>());
        StubUi ui = new StubUi();

        new ListCommand().execute(taskList, ui);

        assertEquals(taskList, ui.lastShownTaskList);
    }

    @Test
    public void isExit_listCommand_returnsFalse() {
        assertFalse(new ListCommand().isExit());
    }
}
