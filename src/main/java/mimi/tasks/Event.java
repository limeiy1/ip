package mimi.tasks;

import mimi.exception.MimiException;



public class Event extends Task {

    private String from;
    private String to;

    public Event(String description, String from, String to) throws MimiException {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
