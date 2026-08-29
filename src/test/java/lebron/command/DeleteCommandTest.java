package lebron.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lebron.exception.LeBronException;
import lebron.task.TaskList;
import lebron.task.TaskListException;
import lebron.task.Todo;
import lebron.ui.StubUi;

public class DeleteCommandTest {
    @Test
    public void execute_validTaskNumber_taskRemovedAndReportedToUi() throws LeBronException {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        StubUi ui = new StubUi();
        Command command = new DeleteCommand(1);

        command.execute(taskList, ui);

        assertEquals(0, taskList.size());
        assertEquals("[T][ ] read book", ui.lastTaskDeleted.toString());
        assertEquals(0, ui.lastTaskDeletedSize);
    }

    @Test
    public void execute_taskNumberOutOfRange_exceptionThrownAndListUnchanged() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        StubUi ui = new StubUi();
        Command command = new DeleteCommand(5);

        assertThrows(TaskListException.class, () -> command.execute(taskList, ui));
        assertEquals(1, taskList.size());
    }
}
