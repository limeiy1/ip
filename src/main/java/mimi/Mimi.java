package mimi;

import mimi.commands.Command;

import mimi.exception.MimiException;
import mimi.storage.Storage;
import mimi.ui.Ui;

public class Mimi {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Mimi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MimiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MimiException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Mimi("./data/mimi.txt").run();
    }
}
