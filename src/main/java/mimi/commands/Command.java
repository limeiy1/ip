package mimi.commands;

import mimi.exception.MimiException;
import mimi.ui.Ui;
import mimi.TaskList;
import mimi.storage.Storage;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException;

    public boolean isExit() {
        return false;
    }
}
