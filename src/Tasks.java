import java.time.DayOfWeek;
import java.time.LocalDate;

import java.util.*;
import java.util.Date;

public enum Tasks {
    TS_ONE_TIME(1, "Однократная"),
    TS_DAILY(2, "Ежедневная"),
    TS_WEEKLY(3, "Еженедельная"),
    TS_MONTHLY(4, "Ежемесячная"),
    TS_ANNUAL(5, "Ежегодная");
    private String nameTask;
    private int id;

    Tasks(int id, String nameTask) {
        this.nameTask = nameTask;
        this.id = id;
    }

    public String[] getAllTasks() {
        String[] tasks = new String[Tasks.values().length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = Tasks.values()[i].nameTask;
        }
        return tasks;
    }

    public String getNameTask() {
        return nameTask;
    }

    public int getId() {
        return id;
    }

    public int getNumberTypeTasks(int number) {
        return 0;
    }

    public void addTypeTask(int number) {

    }

    @Override
    public String toString() {
        return getId() + " " + getNameTask();
    }
}
