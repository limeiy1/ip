package mimi;

import mimi.commands.AddDeadlineCommand;
import mimi.commands.AddEventCommand;
import mimi.commands.AddTodoCommand;
import mimi.commands.Command;
import mimi.commands.DeleteCommand;
import mimi.commands.ExitCommand;
import mimi.commands.ListCommand;
import mimi.commands.MarkCommand;
import mimi.commands.UnmarkCommand;
import mimi.exception.MimiException;

public class Parser {
    public static Command parse(String input) throws MimiException {
        String[] parts = input.trim().split("\\s+", 2);
        String command = parts[0].toLowerCase();

        switch (command) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ListCommand();

        case "mark": {
            checkFormatError(parts, "Missing task number! (format: mark <taskNumber>)");
            int taskNumber = parseIndex(parts[1]);
            return new MarkCommand(taskNumber);
        }

        case "unmark": {
            checkFormatError(parts, "Missing task number! (format: unmark <taskNumber>)");
            int taskNumber = parseIndex(parts[1]);
            return new UnmarkCommand(taskNumber);
        }

        case "delete": {
            checkFormatError(parts, "Missing task number! (format: delete <taskNumber>)");
            int taskNumber = parseIndex(parts[1]);
            return new DeleteCommand(taskNumber);
        }

        case "todo": {
            checkFormatError(parts, "Missing description! (format: todo <description>)");
            return new AddTodoCommand(parts[1]);
        }

        case "deadline": {
            checkFormatError(parts, "Missing description! (format: deadline <description> /by <when>)");
            String[] deadlineDetails = parts[1].split("\\s+/by\\s+", 2);
            checkFormatError(deadlineDetails, "Incorrect format! (format: deadline <description> /by <when>)");

            String taskName = deadlineDetails[0];
            String deadlineDate = deadlineDetails[1];
            return new AddDeadlineCommand(taskName, deadlineDate);
        }

        case "event": {
            checkFormatError(parts, "Missing description! (format: event <description> /from <start> /to <end>)");
            String[] eventDetails = parts[1].split("\\s+/from\\s+", 2);
            checkFormatError(eventDetails, "Incorrect format! (format: event <description> /from <start> /to <end>)");

            String eventName = eventDetails[0];
            String[] eventDates = eventDetails[1].split("\\s+/to\\s+", 2);
            checkFormatError(eventDates, "Incorrect format! (format: event <description> /from <start> /to <end>)");

            String fromDate = eventDates[0];
            String toDate = eventDates[1];
            return new AddEventCommand(eventName, fromDate, toDate);
        }

        default:
            throw new MimiException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void checkFormatError(String[] parts, String message) throws MimiException {
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new MimiException(message);
        }
    }

    private static int parseIndex(String inputNumber) throws MimiException {
        try {
            int index = Integer.parseInt(inputNumber.trim()) - 1;
            if (index < 0) {
                throw new MimiException("Task number should be a positive integer!");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new MimiException("Invalid task number: " + inputNumber);
        }
    }
}
