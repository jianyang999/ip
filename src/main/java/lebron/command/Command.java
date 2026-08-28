package lebron.command;

import lebron.exception.LeBronException;
import lebron.task.TaskList;
import lebron.ui.Ui;

/**
 * Represents a single executable user command.
 */
public interface Command {
    /**
     * Executes this command against the given TaskList, reporting results via Ui.
     *
     * @param taskList The TaskList to act on.
     * @param ui The Ui to report results through.
     * @throws LeBronException if the command cannot be carried out.
     */
    void execute(TaskList taskList, Ui ui) throws LeBronException;

    /**
     * Returns whether this command should terminate the program after executing.
     *
     * @return true if the program should exit, false otherwise.
     */
    default boolean isExit() {
        return false;
    }
}
