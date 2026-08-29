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

public class UnmarkCommandTest {
    @Test
    public void execute_validTaskNumber_taskMarkedNotDoneAndReportedToUi() throws LeBronException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Todo todo = new Todo("read book");
        todo.setStatus(true);
        taskList.addTask(todo);
        StubUi ui = new StubUi();
        Command command = new UnmarkCommand(1);

        command.execute(taskList, ui);

        assertEquals("[T][ ] read book", taskList.getTask(1).toString());
        assertEquals("[T][ ] read book", ui.lastTaskUnmarked.toString());
    }

    @Test
    public void execute_taskNumberOutOfRange_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        StubUi ui = new StubUi();
        Command command = new UnmarkCommand(0);

        assertThrows(TaskListException.class, () -> command.execute(taskList, ui));
    }
}
