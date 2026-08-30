package lebron.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lebron.command.Command;
import lebron.exception.LeBronException;
import lebron.task.TaskList;
import lebron.task.Todo;
import lebron.ui.StubUi;

public class ParserTest {
    /**
     * Parses the input and executes the resulting Command against a fresh TaskList,
     * so we can verify what Parser produced by checking its observable effect
     * (Command's fields are private with no getters).
     */
    private TaskList parseAndExecute(String input, StubUi ui) throws LeBronException {
        TaskList taskList = new TaskList(new ArrayList<>());
        Command command = Parser.parse(input);
        command.execute(taskList, ui);
        return taskList;
    }

    @Test
    public void parse_bye_returnsCommandThatExits() throws LeBronException {
        Command command = Parser.parse("bye");
        assertTrue(command.isExit());
    }

    @Test
    public void parse_list_returnsCommandThatShowsTaskList() throws LeBronException {
        StubUi ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        Command command = Parser.parse("list");

        command.execute(taskList, ui);

        assertEquals(taskList, ui.lastShownTaskList);
    }

    @Test
    public void parse_todoValid_addsTodoWithCorrectDescription() throws LeBronException {
        StubUi ui = new StubUi();
        TaskList taskList = parseAndExecute("todo read book", ui);

        assertEquals(1, taskList.size());
        assertEquals("[T][ ] read book", ui.lastTodoAdded.toString());
    }

    @Test
    public void parse_todoMissingDescription_exceptionThrown() {
        assertThrows(LeBronException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void parse_todoBlankDescription_exceptionThrown() {
        assertThrows(LeBronException.class, () -> Parser.parse("todo    "));
    }

    @Test
    public void parse_deadlineValid_addsDeadlineWithCorrectDescriptionAndDate() throws LeBronException {
        StubUi ui = new StubUi();
        TaskList taskList = parseAndExecute("deadline return book by 2019-10-15 1800", ui);

        assertEquals(1, taskList.size());
        assertEquals("[D][ ] return book (by: Oct 15 2019)", ui.lastDeadlineAdded.toString());
    }

    @Test
    public void parse_deadlineMissingDescription_exceptionThrown() {
        assertThrows(LeBronException.class, () -> Parser.parse("deadline"));
    }

    @Test
    public void parse_deadlineMissingByKeyword_exceptionThrown() {
        assertThrows(LeBronException.class, () -> Parser.parse("deadline return book 2019-10-15 1800"));
    }

    @Test
    public void parse_deadlineMalformedDate_dateTimeParseExceptionThrown() {
        assertThrows(DateTimeParseException.class,
                () -> Parser.parse("deadline return book by not-a-date"));
    }

    @Test
    public void parse_eventValid_addsEventWithCorrectDescriptionAndDates() throws LeBronException {
        StubUi ui = new StubUi();
        TaskList taskList = parseAndExecute("event project meeting from 2019-10-16 0900 to 2019-10-16 1100", ui);

        assertEquals(1, taskList.size());
        assertEquals("[E][ ] project meeting (from Oct 16 2019 til Oct 16 2019)", ui.lastEventAdded.toString());
    }

    @Test
    public void parse_eventMissingDescription_exceptionThrown() {
        assertThrows(LeBronException.class, () -> Parser.parse("event"));
    }

    @Test
    public void parse_eventMissingFromToKeywords_exceptionThrown() {
        assertThrows(LeBronException.class, () -> Parser.parse("event project meeting 2019-10-16 0900"));
    }

    @Test
    public void parse_markValid_marksCorrectTask() throws LeBronException {
        StubUi ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));

        Command command = Parser.parse("mark 1");
        command.execute(taskList, ui);

        assertEquals("[T][X] read book", ui.lastTaskMarked.toString());
    }

    @Test
    public void parse_unmarkValid_unmarksCorrectTask() throws LeBronException {
        StubUi ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        taskList.getTask(1).setStatus(true);

        Command command = Parser.parse("unmark 1");
        command.execute(taskList, ui);

        assertEquals("[T][ ] read book", ui.lastTaskUnmarked.toString());
    }

    @Test
    public void parse_deleteValid_deletesCorrectTask() throws LeBronException {
        StubUi ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));

        Command command = Parser.parse("delete 1");
        command.execute(taskList, ui);

        assertEquals(0, taskList.size());
        assertEquals("[T][ ] read book", ui.lastTaskDeleted.toString());
    }

    @Test
    public void parse_findValid_reportsOnlyMatchingTasksToUi() throws LeBronException {
        StubUi ui = new StubUi();
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("write essay"));

        Command command = Parser.parse("find book");
        command.execute(taskList, ui);

        assertEquals(1, ui.lastMatchingTasks.size());
        assertEquals("[T][ ] read book", ui.lastMatchingTasks.get(0).toString());
    }

    @Test
    public void parse_findMissingKeyword_exceptionThrown() {
        assertThrows(LeBronException.class, () -> Parser.parse("find"));
    }

    @Test
    public void parse_findBlankKeyword_exceptionThrown() {
        assertThrows(LeBronException.class, () -> Parser.parse("find    "));
    }

    @Test
    public void parse_unknownCommand_exceptionThrown() {
        assertThrows(LeBronException.class, () -> Parser.parse("frobnicate"));
    }
}
