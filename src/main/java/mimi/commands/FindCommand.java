package mimi.commands;

import mimi.TaskList;
import mimi.exception.MimiException;
import mimi.storage.Storage;
import mimi.tasks.Task;
import mimi.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    public String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        ArrayList<Task> matches = tasks.find(keyword);
        ui.showFoundTasks(matches);
        storage.save(tasks.asList());
    }
}
