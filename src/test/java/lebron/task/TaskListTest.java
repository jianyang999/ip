package lebron.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void constructor_givenList_createsDefensiveCopy() {
        ArrayList<Task> original = new ArrayList<>();
        original.add(new Todo("read book"));
        TaskList taskList = new TaskList(original);

        original.add(new Todo("write essay"));

        assertEquals(1, taskList.size());
    }

    @Test
    public void addTask_onEmptyList_sizeBecomesOne() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        assertEquals(1, taskList.size());
    }

    @Test
    public void getTask_validIndex_returnsCorrectTask() throws TaskListException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Task task = new Todo("read book");
        taskList.addTask(task);

        assertEquals(task, taskList.getTask(1));
    }

    @Test
    public void getTask_indexZero_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));

        assertThrows(TaskListException.class, () -> taskList.getTask(0));
    }

    @Test
    public void getTask_indexPastEnd_exceptionThrown() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));

        assertThrows(TaskListException.class, () -> taskList.getTask(2));
    }

    @Test
    public void deleteTask_validIndex_taskRemovedAndSizeDecreases() throws TaskListException {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("write essay"));

        taskList.deleteTask(1);

        assertEquals(1, taskList.size());
        assertEquals("[T][ ] write essay", taskList.getTask(1).toString());
    }

    @Test
    public void deleteTask_indexZero_exceptionThrownAndListUnchanged() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));

        assertThrows(TaskListException.class, () -> taskList.deleteTask(0));
        assertEquals(1, taskList.size());
    }

    @Test
    public void deleteTask_indexPastEnd_exceptionThrownAndListUnchanged() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));

        assertThrows(TaskListException.class, () -> taskList.deleteTask(2));
        assertEquals(1, taskList.size());
    }

    @Test
    public void findTasks_keywordMatchesSome_returnsOnlyMatchingTasksInOrder() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("write essay"));
        taskList.addTask(new Todo("return book"));

        ArrayList<Task> matches = taskList.findTasks("book");

        assertEquals(2, matches.size());
        assertEquals("[T][ ] read book", matches.get(0).toString());
        assertEquals("[T][ ] return book", matches.get(1).toString());
    }

    @Test
    public void findTasks_keywordDifferentCase_stillMatches() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read Book"));

        assertEquals(1, taskList.findTasks("book").size());
    }

    @Test
    public void findTasks_noKeywordMatch_returnsEmptyList() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));

        assertEquals(0, taskList.findTasks("essay").size());
    }

    @Test
    public void size_emptyList_returnsZero() {
        TaskList taskList = new TaskList(new ArrayList<>());
        assertEquals(0, taskList.size());
    }

    @Test
    public void reformat_multipleTasks_onePerLine() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("write essay"));

        assertEquals("T | 0 | read book\nT | 0 | write essay\n", taskList.reformat());
    }

    @Test
    public void toString_multipleTasks_numberedFromOne() {
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("write essay"));

        assertEquals("1. [T][ ] read book\n2. [T][ ] write essay\n", taskList.toString());
    }
}
