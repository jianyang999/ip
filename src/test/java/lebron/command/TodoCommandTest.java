package lebron.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lebron.exception.LeBronException;
import lebron.task.TaskList;
import lebron.ui.StubUi;

public class TodoCommandTest {
    @Test
    public void execute_validDescription_taskAddedToListAndReportedToUi() throws LeBronException {
        TaskList taskList = new TaskList(new ArrayList<>());
        StubUi ui = new StubUi();
        Command command = new TodoCommand("read book");

        command.execute(taskList, ui);

        assertEquals(1, taskList.size());
        assertEquals("[T][ ] read book", ui.lastTodoAdded.toString());
        assertEquals(1, ui.lastTodoAddedSize);
    }

    @Test
    public void isExit_todoCommand_returnsFalse() {
        assertEquals(false, new TodoCommand("read book").isExit());
    }
}
