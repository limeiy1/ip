package mimi.commands;

import mimi.storage.Storage;
import mimi.TaskList;
import mimi.ui.Ui;
import mimi.exception.MimiException;
import mimi.tasks.Task;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int taskNumber) {
        this.index = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        if (index >= tasks.size()) {
            throw new MimiException("Task number is out of list range! Enter a valid task number!");
        }
        Task taskName = tasks.get(index);
        tasks.unmark(index);
        ui.showUnmark(taskName);
        storage.save(tasks.asList());
    }
}
