package task;

import calendar.Calendar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MenuTask {
    Calendar1 calendar;
    boolean bool;

    public MenuTask() {
        this.calendar = new Calendar1();
    }

    void start() {
        this.bool = true;
    }

    boolean breakMenu() {
        return this.bool = false;
    }

     public void showMenu() {
        start();
        while (bool) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            try {

                System.out.println("1 - Просмотр календаря\n" + "2 - Добавить план в календарь\n" + "3 - Отфильтровать календарь по приоритету задач\n" +
                "4 - Отфильтровать задачи на свои / чужие \n" + "5 - Редактировать задачу\n" + "6 - выйти из меню \n" + "7 - Создание рандомной задачи.\n"
                        + "8 - Записать колендарь");
                int cas = Integer.parseInt(bufferedReader.readLine());
                switch (cas) {
                    case (1):
                        calendar.getCal();
                        break;
                    case (2):
                        calendar.addMyTask();
                        break;
                    case(3):
                        System.out.println("Выбери приоритет low / mid / high");
                        Prior p = Prior.valueOf(bufferedReader.readLine());
                        calendar.filterlistTask(p);
                        break;

                    case (4):
                        System.out.println("1 - свои, 2 - чужие");
                        boolean b = (bufferedReader.readLine().equals("1"));
                        calendar.getTasks(b);
                        break;
                    case(5):
                        System.out.println("Введите название задачи.");
                        String buff = bufferedReader.readLine();
                        calendar.repeattask(buff);
                        break;
                    case (6):
                        breakMenu();
                        break;
                    case (7):
                        System.out.println("Добавлена новая рандомная задача.");
                        generatorCal generatorCal1 = new generatorCal();
                        Calendar1.taskList.add(generatorCal1.rand());
                        break;
                    case (8):
                        System.out.println("Запись календаря.");
                        calendar.writeCalendar();
                        break;
                    default: breakMenu();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
