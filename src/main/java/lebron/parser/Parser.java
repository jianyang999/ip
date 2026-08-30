package lebron.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lebron.command.ByeCommand;
import lebron.command.Command;
import lebron.command.DeadlineCommand;
import lebron.command.DeleteCommand;
import lebron.command.EventCommand;
import lebron.command.FindCommand;
import lebron.command.ListCommand;
import lebron.command.MarkCommand;
import lebron.command.TodoCommand;
import lebron.command.UnmarkCommand;
import lebron.exception.LeBronException;

/**
 * Deals with making sense of raw user input, turning it into an executable Command.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    private Parser() {
    }

    /**
     * Bundles the description and due date/time parsed out of a "deadline" command.
     */
    private record DeadlineArgs(String description, LocalDateTime by) {
    }

    /**
     * Bundles the description and start/end date/time parsed out of an "event" command.
     */
    private record EventArgs(String description, LocalDateTime start, LocalDateTime end) {
    }

    /**
     * Parses raw user input into an executable Command.
     *
     * @param input Raw user input.
     * @return The Command corresponding to the input.
     * @throws LeBronException if the input is not a recognised, well-formed command.
     */
    public static Command parse(String input) throws LeBronException {
        CommandType commandType = getCommandType(input);
        switch (commandType) {
            case BYE -> {
                return new ByeCommand();
            }
            case LIST -> {
                return new ListCommand();
            }
            case TODO -> {
                return new TodoCommand(parseTodoDescription(input));
            }
            case DEADLINE -> {
                DeadlineArgs args = parseDeadlineArgs(input);
                return new DeadlineCommand(args.description(), args.by());
            }
            case EVENT -> {
                EventArgs args = parseEventArgs(input);
                return new EventCommand(args.description(), args.start(), args.end());
            }
            case MARK -> {
                return new MarkCommand(parseTaskNumber(input));
            }
            case UNMARK -> {
                return new UnmarkCommand(parseTaskNumber(input));
            }
            case DELETE -> {
                return new DeleteCommand(parseTaskNumber(input));
            }
            case FIND -> {
                return new FindCommand(parseFindKeyword(input));
            }
            default -> throw new LeBronException("Whatchu tryna do youngblood?");
        }
    }

    /**
     * Determines the CommandType of a raw user input line.
     *
     * @param input Raw user input.
     * @return The CommandType corresponding to the input's first word.
     */
    private static CommandType getCommandType(String input) {
        String firstWord = input.split(" ")[0];
        switch (firstWord) {
            case "bye":
                return CommandType.BYE;
            case "list":
                return CommandType.LIST;
            case "mark":
                return CommandType.MARK;
            case "unmark":
                return CommandType.UNMARK;
            case "todo":
                return CommandType.TODO;
            case "deadline":
                return CommandType.DEADLINE;
            case "event":
                return CommandType.EVENT;
            case "delete":
                return CommandType.DELETE;
            case "find":
                return CommandType.FIND;
            default:
                return CommandType.UNKNOWN;
        }
    }

    /**
     * Extracts and validates the description from a "todo" command.
     *
     * @param input Raw user input, e.g. "todo read book".
     * @return The Todo's description.
     * @throws LeBronException if no description was given.
     */
    private static String parseTodoDescription(String input) throws LeBronException {
        String description = input.length() > 4 ? input.substring(5).trim() : "";
        if (description.isEmpty()) {
            throw new LeBronException("Whatchu tryna to do?");
        }
        return description;
    }

    /**
     * Extracts and validates the description and due date/time from a "deadline" command.
     *
     * @param input Raw user input, e.g. "deadline return book by 2019-10-15 1800".
     * @return The parsed description and due date/time.
     * @throws LeBronException if no description/date was given, or the "by" keyword is missing.
     */
    private static DeadlineArgs parseDeadlineArgs(String input) throws LeBronException {
        String fullDesc = input.length() > 8 ? input.substring(9).trim() : "";
        if (fullDesc.isEmpty()) {
            throw new LeBronException("Yo specify your deadline!");
        }
        if (!fullDesc.contains(" by ")) {
            throw new LeBronException("Stop playin with me!");
        }
        String[] parts = fullDesc.split(" by ");
        String description = parts[0];
        LocalDateTime by = LocalDateTime.parse(parts[1].trim(), INPUT_DATE_FORMAT);
        return new DeadlineArgs(description, by);
    }

    /**
     * Extracts and validates the description and start/end date/time from an "event" command.
     *
     * @param input Raw user input, e.g. "event project meeting from 2019-10-16 0900 to 2019-10-16 1100".
     * @return The parsed description and start/end date/time.
     * @throws LeBronException if no description/dates were given, or the "from"/"to" keywords are missing.
     */
    private static EventArgs parseEventArgs(String input) throws LeBronException {
        String fullDesc = input.length() > 5 ? input.substring(6).trim() : "";
        if (fullDesc.isEmpty()) {
            throw new LeBronException("What event you tryna go for? Quit playin!");
        }
        if (!fullDesc.contains(" from ") || !fullDesc.contains(" to ")) {
            throw new LeBronException("Tell me start and end!");
        }
        String[] parts = fullDesc.split(" from | to ");
        String description = parts[0];
        LocalDateTime start = LocalDateTime.parse(parts[1].trim(), INPUT_DATE_FORMAT);
        LocalDateTime end = LocalDateTime.parse(parts[2].trim(), INPUT_DATE_FORMAT);
        return new EventArgs(description, start, end);
    }

    /**
     * Extracts and validates the keyword from a "find" command.
     *
     * @param input Raw user input, e.g. "find book".
     * @return The search keyword.
     * @throws LeBronException if no keyword was given.
     */
    private static String parseFindKeyword(String input) throws LeBronException {
        String keyword = input.length() > 4 ? input.substring(5).trim() : "";
        if (keyword.isEmpty()) {
            throw new LeBronException("Whatchu tryna find?");
        }
        return keyword;
    }

    /**
     * Extracts the task number from a "mark"/"unmark"/"delete" command.
     *
     * @param input Raw user input, e.g. "mark 2".
     * @return The 1-based task number.
     */
    private static int parseTaskNumber(String input) {
        return Integer.parseInt((input.split(" "))[1]);
    }
}
