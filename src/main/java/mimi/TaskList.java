package mimi;

import mimi.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            String taskDescription = (task.toString()).toLowerCase();
            if (taskDescription.contains(keyword.toLowerCase())) {
                matches.add(task);
            }
        }
        return matches;
    }

    public void add(Task taskName) {
        tasks.add(taskName);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public void mark(int index) {
        Task t = tasks.get(index);
        t.markAsDone();
    }

    public void unmark(int index) {
        Task t = tasks.get(index);
        t.unmarkAsDone();
    }

    public ArrayList<Task> asList() {
        return new ArrayList<>(tasks);
    }
}
