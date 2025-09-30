package mimi.commands;

import mimi.storage.Storage;
import mimi.TaskList;
import mimi.ui.Ui;
import mimi.exception.MimiException;
import mimi.tasks.Deadline;

public class AddDeadlineCommand extends Command {
    private final String taskName;
    private final String deadlineDate;

    public AddDeadlineCommand(String taskName, String deadlineDate) {
        this.taskName = taskName;
        this.deadlineDate = deadlineDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MimiException {
        Deadline newDeadline = new Deadline(taskName, deadlineDate);
        tasks.add(newDeadline);
        ui.showAddedTask(newDeadline, tasks.size());
        storage.save(tasks.asList());
    }
}
