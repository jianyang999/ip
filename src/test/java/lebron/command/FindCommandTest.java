package lebron.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lebron.task.TaskList;
import lebron.task.Todo;
import lebron.ui.StubUi;

public class FindCommandTest {
    @Test
    public void execute_matchingKeyword_reportsMatchingTasksToUi() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("write essay"));
        StubUi ui = new StubUi();

        new FindCommand("book").execute(taskList, ui);

        assertEquals(1, ui.lastMatchingTasks.size());
        assertEquals("[T][ ] read book", ui.lastMatchingTasks.get(0).toString());
    }

    @Test
    public void execute_noMatch_reportsEmptyListToUi() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        StubUi ui = new StubUi();

        new FindCommand("essay").execute(taskList, ui);

        assertEquals(0, ui.lastMatchingTasks.size());
    }

    @Test
    public void isExit_findCommand_returnsFalse() {
        assertFalse(new FindCommand("book").isExit());
    }
}
