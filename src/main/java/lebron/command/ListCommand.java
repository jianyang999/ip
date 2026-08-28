package lebron.command;

import lebron.task.TaskList;
import lebron.ui.Ui;

/**
 * Represents the "list" command, which displays the current TaskList.
 */
public class ListCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showTaskList(taskList);
    }
}
