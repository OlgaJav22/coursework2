import java.time.LocalDate;
import java.util.*;

public class TaskService<S extends NewTask> {

    private Map<Integer, NewTask> tasksMap;

    TaskService() {

        tasksMap = new HashMap<>();
    }

    public void addTask(NewTask task) {
        tasksMap.put(task.getId(), task);
    }

    public List<NewTask> getTaskByDate(LocalDate date) {
        List<NewTask> tasksList = new ArrayList<>();
        for (Map.Entry<Integer, NewTask> entry : tasksMap.entrySet()) {
            NewTask task = entry.getValue();
            if (task.checkDateTask(date)
                    || !task.checkDateTask(date) && task.getDateOfCreation().toLocalDate().equals(date)) {
                tasksList.add(task);
            }
        }
        tasksList.sort(Comparator.comparing(NewTask::getDateOfCreation));
        return tasksList;
    }

    @Override
    public String toString() {
        return tasksMap.toString();
    }

}



