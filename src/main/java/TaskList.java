import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList with the given list of tasks.
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the TaskList.
     *
     * @param taskNumber The 1 based index of the task removed from TaskList.
     * @throws TaskListException if taskNumber is not in range.
     */
    public void deleteTask(int taskNumber) throws TaskListException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new TaskListException("This task does not exist kid.");
        }
        this.tasks.remove(taskNumber - 1);
    }

    /**
     * Retrieves a task from the TaskList.
     *
     * @param taskNumber The 1 based index of the task retrieved from TaskList.
     * @return The task at the specified index.
     * @throws TaskListException if taskNumber is not in range.
     */
    public Task getTask(int taskNumber) throws TaskListException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new TaskListException("This task does not exist kid.");
        }
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return The size of the list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Reformats the TaskList to be saved using Storage
     *
     * @return Reformatted String.
     */
    public String reformat() {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            sb.append(task.reformat()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }
}
