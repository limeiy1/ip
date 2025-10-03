package mimi.commands;

import mimi.storage.Storage;
import mimi.TaskList;
import mimi.ui.Ui;
import mimi.exception.MimiException;
import mimi.tasks.Event;

/**
 * Adds an event to the task list.
 */
public class AddEventCommand extends Command {
    private final String eventName;
    private final String fromDate;
    private final String toDate;

    /**
     * Constructs an AddEvent command.
     * @param eventName name of the event
     * @param fromDate start date of the event
     * @param toDate end date of the event
     */
    public AddEventCommand(String eventName, String fromDate, String toDate) {
        this.eventName = eventName;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Executes the AddEvent command.
     * Constructs a new Event, adds it to the task list.
     * Displays the added event message to user.
     * Saves the updated task list to the data file.
     * @param tasks the current task list
     * @param ui the ui for printing output
     * @param storage the storage where task list data is saved
     * @throws MimiException if error occurs
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        Event newEvent = new Event(eventName, fromDate, toDate);
        tasks.add(newEvent);
        ui.showAddedTask(newEvent, tasks.size());
        storage.save(tasks.asList());
    }
}
