package mimi.commands;

import mimi.storage.Storage;
import mimi.TaskList;
import mimi.ui.Ui;
import mimi.exception.MimiException;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        ui.showTaskList(tasks);
    }
}
