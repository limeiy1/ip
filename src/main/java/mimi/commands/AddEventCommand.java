package mimi.commands;

import mimi.storage.Storage;
import mimi.TaskList;
import mimi.ui.Ui;
import mimi.exception.MimiException;
import mimi.tasks.Event;

public class AddEventCommand extends Command {
    private final String eventName;
    private final String fromDate;
    private final String toDate;

    public AddEventCommand(String eventName, String fromDate, String toDate) {
        this.eventName = eventName;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        Event newEvent = new Event(eventName, fromDate, toDate);
        tasks.add(newEvent);
        ui.showAddedTask(newEvent, tasks.size());
        storage.save(tasks.asList());
    }
}
