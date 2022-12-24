import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class NewTask {
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private TaskService taskService;
    private String nameTask;
    protected LocalDateTime dateOfCreation;
    private boolean personalTask;
    private String taskDescription;
    private Integer id;
    private int numberTypeTask;
    public static int count = 0;
    public Map<Integer, LocalDateTime> tasksMap;

    public NewTask() {

    }

    public NewTask(String nameTask, int numberTypeTask, LocalDateTime dateOfCreation, String taskDescription, boolean personalTask) {

        this.nameTask = enterTask(nameTask);
        if (this.nameTask != null && !this.nameTask.isEmpty()) {
            System.out.println("Выберите тип задачи из списка:\n1.Однократная\n2.Ежедневная\n3.Еженедельная\n4.Ежемесячная\n5.Ежегодная" + "\t");
            this.numberTypeTask = getNumberTypeTasks(numberTypeTask);
            this.dateOfCreation = getDateCreation(dateOfCreation);
        } else {
            throw new RuntimeException("Неверный формат наименования");
        }
        this.taskDescription = enterTaskDescription(taskDescription);
        if (this.taskDescription != null && !this.taskDescription.isEmpty() && !this.taskDescription.isBlank()) {
            this.personalTask = isPersonalTask();

            count++;
            this.id = count;
            tasksMap = new LinkedHashMap<>();
            tasksMap.put(this.id, this.dateOfCreation);
            System.out.println(tasksMap.toString());

//            taskService.addTask();
        } else {
            throw new RuntimeException("Необходимо заполнить описание");

        }
    }

    public int getNumberTypeTasks(int number) {
        Scanner scr = new Scanner(System.in);
        number = scr.nextInt();
        if (number > 0) {
            addTypeTask(number);
        } else {
            System.out.println("Необходимо выбрать тип задачи");
        }
        return number;
    }

    public void addTypeTask(int number) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar calendar = Calendar.getInstance();

        if (number == Tasks.TS_WEEKLY.getId()) {
            calendar.add(calendar.WEEK_OF_MONTH, 1);
            System.out.println("Следующая дата выполнения: " + dateFormat.format(calendar.getTime()));
        } else if (number == Tasks.TS_MONTHLY.getId()) {
            calendar.add(calendar.MONTH, 1);
            System.out.println("Следующая дата выполнения: " + dateFormat.format(calendar.getTime()));
        } else if (number == Tasks.TS_ANNUAL.getId()) {
            calendar.add(calendar.YEAR, 1);
            System.out.println("Следующая дата выполнения: " + dateFormat.format(calendar.getTime()));
        } else if (number == Tasks.TS_DAILY.getId()) {
            calendar.add(calendar.DAY_OF_YEAR, 1);
            System.out.println("Следующая дата выполнения: " + dateFormat.format(calendar.getTime()));
        } else if (number == Tasks.TS_ONE_TIME.getId()) {

        } else {
            throw new RuntimeException("Некорректное значение");
        }
    }

    public boolean checkDateTask(LocalDate date) {
        if (Tasks.TS_DAILY.getId() == 2) {
            return true;
        } else if (Tasks.TS_WEEKLY.getId() == 3) {
            return date.getDayOfWeek() == getDateOfCreation().getDayOfWeek();
        } else if (Tasks.TS_MONTHLY.getId() == 4) {
            return date.getDayOfMonth() == getDateOfCreation().getDayOfMonth();
        } else if (Tasks.TS_ANNUAL.getId() == 5) {
            return date.getDayOfMonth() == getDateOfCreation().getDayOfMonth() && date.getMonth() == getDateOfCreation().getMonth();
        }
        return true;
    }

    public String enterTask(String nameTask) {
        Scanner skr = new Scanner(System.in);
        System.out.println("Введите наименование задачи");
        nameTask = skr.nextLine();
        return nameTask;

    }

    public String enterTaskDescription(String taskDescription) {
        Scanner skr = new Scanner(System.in);
        System.out.println("Описание задачи");
        taskDescription = skr.nextLine();
        return taskDescription.toString();

    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getNumberTypeTask() {
        return numberTypeTask;
    }

    public Integer getId() {
        return id;
    }

    public String getNameTask() {
        return enterTask(nameTask);
    }

    public LocalDateTime getDateCreation(LocalDateTime dateTime) {
        dateTime = LocalDateTime.now();
        System.out.println(dateTime.format(DATE));
        return dateTime;
    }

    public LocalDateTime getDateOfCreation() {
        return getDateCreation(dateOfCreation);
    }

    public boolean isPersonalTask() {
        return personalTask;
    }

    @Override
    public String toString() {
        return getNameTask() + " " + dateOfCreation.format(DATE) + " " + getId() + tasksMap;
    }
}
