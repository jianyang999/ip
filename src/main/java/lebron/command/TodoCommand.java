package lebron.command;

import lebron.task.Task;
import lebron.task.TaskList;
import lebron.task.Todo;
import lebron.ui.Ui;

/**
 * Represents the "todo" command, which adds a Todo to the TaskList.
 */
public class TodoCommand implements Command {
    private final String description;

    /**
     * Constructs a TodoCommand with the given description.
     *
     * @param description The Todo's description.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task task = new Todo(description);
        taskList.addTask(task);
        ui.showTodoAdded(task, taskList.size());
    }
}
