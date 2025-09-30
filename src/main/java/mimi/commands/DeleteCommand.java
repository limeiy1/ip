package mimi.commands;

import mimi.storage.Storage;
import mimi.TaskList;
import mimi.ui.Ui;
import mimi.exception.MimiException;
import mimi.tasks.Task;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int taskNumber) {
        this.index = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        if (index >= tasks.size()) {
            throw new MimiException("Task number is out of list range! Enter a valid task number!");
        }
        Task taskName = tasks.get(index);
        tasks.delete(index);
        ui.showDeletedTask(taskName, tasks.size());
        storage.save(tasks.asList());
    }
}
