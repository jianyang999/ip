import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for saving and loading a TaskList to and from disk.
 */
public class Storage {

    /**
     * Saves the given TaskList to disk.
     *
     * @param tasks TaskList to be saved.
     */
    public void save(TaskList tasks) throws IOException {
        Path filePath = Paths.get("data", "LeBron.txt");
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
        Path filePath = Paths.get("data", "LeBron.txt");
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
            task = new Deadline(description, parts[3]);
            break;
        case "E":
            task = new Event(description, parts[3], parts[4]);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type in save file: " + line);
        }

        task.setStatus(isDone);
        return task;
    }
}
