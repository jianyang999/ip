package lebron.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import lebron.task.TaskList;
import lebron.ui.StubUi;

public class ByeCommandTest {
    @Test
    public void isExit_alwaysReturnsTrue() {
        assertTrue(new ByeCommand().isExit());
    }

    @Test
    public void execute_showsGoodbyeMessage() {
        StubUi ui = new StubUi();
        new ByeCommand().execute(new TaskList(new ArrayList<>()), ui);

        assertEquals("Peace out see ya later!", ui.lastMessage);
    }
}
