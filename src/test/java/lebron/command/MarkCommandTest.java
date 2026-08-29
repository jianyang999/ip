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

public class MarkCommandTest {
    @Test
    public void execute_validTaskNumber_taskMarkedDoneAndReportedToUi() throws LeBronException {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        StubUi ui = new StubUi();
        Command command = new MarkCommand(1);

        command.execute(taskList, ui);

        assertEquals("[T][X] read book", taskList.getTask(1).toString());
        assertEquals("[T][X] read book", ui.lastTaskMarked.toString());
    }

    @Test
    public void execute_taskNumberOutOfRange_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        StubUi ui = new StubUi();
        Command command = new MarkCommand(2);

        assertThrows(TaskListException.class, () -> command.execute(taskList, ui));
    }
}
