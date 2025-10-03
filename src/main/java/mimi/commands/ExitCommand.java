package mimi.commands;

import mimi.storage.Storage;
import mimi.TaskList;
import mimi.ui.Ui;
import mimi.exception.MimiException;

/**
 * Terminates the programme.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
