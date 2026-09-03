package lebron.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lebron.task.Deadline;
import lebron.task.Event;
import lebron.task.Task;
import lebron.task.TaskList;
import lebron.task.Todo;

/**
 * Class responsible for saving and loading a TaskList to and from disk.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage that reads/writes the default save location, {@code data/LeBron.txt}.
     */
    public Storage() {
        this(Paths.get("data", "LeBron.txt"));
    }

    /**
     * Constructs a Storage that reads/writes the given file path.
     * Mainly useful for testing against a temporary file instead of the real save data.
     *
     * @param filePath The file to save to and load from.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given TaskList to disk.
     *
     * @param tasks TaskList to be saved.
     * @throws IOException if the save directory or file cannot be written to.
     */
    public void save(TaskList tasks) throws IOException {
        Files.createDirectories(filePath.getParent());
        Files.writeString(filePath, tasks.reformat());
    }

    /**
     * Loads a TaskList from disk. If no save file exists yet (e.g. first run),
     * an empty TaskList is returned.
     *
     * @return The loaded TaskList.
     * @throws IOException if the save file exists but cannot be read.
     */
    public TaskList load() throws IOException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return new TaskList(loadedTasks);
        }

        List<String> lines = Files.readAllLines(filePath);
        for (String line : lines) {
            if (line.isBlank()) {
                continue;
            }
            loadedTasks.add(parseTask(line));
        }
        return new TaskList(loadedTasks);
    }

    /**
     * Parses a single save-format line (as written by {@link Task#reformat()})
     * back into the corresponding Task subclass.
     *
     * @param line The save-format line, e.g. "D | 0 | return book | June 6th".
     * @return The reconstructed Task.
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadline(description, LocalDateTime.parse(parts[3]));
                break;
            case "E":
                task = new Event(description, LocalDateTime.parse(parts[3]),
                        LocalDateTime.parse(parts[4]));
                break;
            default:
                throw new IllegalArgumentException("Unknown task type in save file: " + line);
        }

        task.setStatus(isDone);
        return task;
    }
}
