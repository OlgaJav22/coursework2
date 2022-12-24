import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {
    private static TaskService taskService;
    private static LocalDateTime dateTime;

    public static void main(String[] args) {
        taskService = new TaskService<>();
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            NewTask number1 = new NewTask("", 1, dateTime, "", true);

                            break;
                        case 2:
                            // todo: обрабатываем пункт меню 2
                            break;
                        case 3:

                            getTasksForDay(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }

    }

    private static void printMenu() {
        System.out.println("\n1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n0. Выход");
    }

    public static void getTasksForDay(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введите дату в формате (01/01/2000):");
        LocalDate date = null;
        boolean enterAgain = true;
        while (enterAgain) {
            try {
                date = LocalDate.parse(scanner.nextLine(), NewTask.DATE);
                enterAgain = false;
            } catch (DateTimeException e) {
                System.out.println("Введите правильный формат даты");
            }
        }
        System.out.println(taskService.getTaskByDate(date));
    }

}