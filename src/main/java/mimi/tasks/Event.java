package mimi.tasks;

/**
 * Represents an event task with start and end date.
 */
public class Event extends Task {

    private final String from;
    private final String to;

    /**
     * Constructs a new Event with description, start date, and end date.
     * @param description the description of the event
     * @param from the start date
     * @param to the end date
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event for output.
     * Includes the task type, completion status, description, start date, and end date.
     * @return string representation for output
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns a string representation of the event in the format to save.
     * @return string representation to save
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
