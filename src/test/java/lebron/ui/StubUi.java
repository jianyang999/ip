package lebron.ui;

import lebron.task.Task;
import lebron.task.TaskList;

/**
 * A test double for Ui that records what it was told to show instead of printing to
 * the console. Lets Command tests assert on what Ui was called with, without
 * depending on Ui's exact printed text or polluting test output.
 */
public class StubUi extends Ui {
    public String lastMessage;
    public TaskList lastShownTaskList;
    public Task lastTodoAdded;
    public int lastTodoAddedSize;
    public Task lastDeadlineAdded;
    public int lastDeadlineAddedSize;
    public Task lastEventAdded;
    public int lastEventAddedSize;
    public Task lastTaskDeleted;
    public int lastTaskDeletedSize;
    public Task lastTaskMarked;
    public Task lastTaskUnmarked;

    @Override
    public void showBanner() {
        // no-op: avoid printing during tests
    }

    @Override
    public void showMessage(String message) {
        this.lastMessage = message;
    }

    @Override
    public void showTaskList(TaskList taskList) {
        this.lastShownTaskList = taskList;
    }

    @Override
    public void showTodoAdded(Task task, int taskListSize) {
        this.lastTodoAdded = task;
        this.lastTodoAddedSize = taskListSize;
    }

    @Override
    public void showDeadlineAdded(Task task, int taskListSize) {
        this.lastDeadlineAdded = task;
        this.lastDeadlineAddedSize = taskListSize;
    }

    @Override
    public void showEventAdded(Task task, int taskListSize) {
        this.lastEventAdded = task;
        this.lastEventAddedSize = taskListSize;
    }

    @Override
    public void showTaskDeleted(Task task, int taskListSize) {
        this.lastTaskDeleted = task;
        this.lastTaskDeletedSize = taskListSize;
    }

    @Override
    public void showTaskMarked(Task task) {
        this.lastTaskMarked = task;
    }

    @Override
    public void showTaskUnmarked(Task task) {
        this.lastTaskUnmarked = task;
    }
}
