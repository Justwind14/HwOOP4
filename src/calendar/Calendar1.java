package calendar;

import task.Mytask;
import task.Othertask;
import task.Prior;
import task.Task;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calendar1 {
    public static List<Task> taskList = new ArrayList<>();
    public static Map<LocalDate, List<LocalTime>> localDateListMap = new HashMap<>();

    private static List<Task> notifTrueList = new ArrayList<>();
    private static List<Task> filterlist = new ArrayList<>();


    public void addMyTask() {
        try {
            {
                Task m = createTask();
                taskList.add(m);
                localDateListMap.computeIfAbsent(m.getDateCreateTask(), k -> new ArrayList<>()).add(m.getStartTask());
                localDateListMap.computeIfAbsent(m.getDateCreateTask(), k -> new ArrayList<>()).add(m.getEndTask());

            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private Task createTask() {
        Task resultTask;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        LocalDate date = null;
        LocalTime starttime = null;
        LocalTime endtime = null;
        try {
            boolean check = true;
            while (check) {

                System.out.println("Введите год, месяц и день - через ENTER");
                date = LocalDate.of(createInts(), createInts(), createInts());
                System.out.println("Введите время начала час и минуты через ENTER");
                starttime = LocalTime.of(createInts(), createInts());
                System.out.println("Введите время выполнения в минутах");
                int a = Integer.parseInt(bf.readLine());
                endtime = starttime.plusMinutes(a);
                if (checklist(date, starttime, endtime)) {
                    check = false;
                } else {
                    System.out.println("Дата занята");
                }
            }
            System.out.println("Введите название задачи");
            String name = bf.readLine();
            System.out.println("Введите описание");
            String discrp = bf.readLine();
            System.out.println("Нотификатион false / true");
            boolean notif = Boolean.parseBoolean(bf.readLine());
            System.out.println("Введите приоритет low or mid or high");
            Prior type = Prior.valueOf(bf.readLine());
            int hash = this.hashCode();
            System.out.println("Задача для себя? да");
            resultTask = !bf.readLine().equals("да") ? new Mytask(hash, name, discrp, notif, date, starttime, endtime, type) : new Othertask(hash, name, discrp, notif, date, starttime, endtime, type);

            if (resultTask.isNotification()) {
                notifTrueList.add(resultTask);
            }
            return resultTask;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean checklist(LocalDate date, LocalTime starttime, LocalTime endtime) {
        boolean b = true;

        for (Map.Entry<LocalDate, List<LocalTime>> a : localDateListMap.entrySet()) {
            if (a.getKey().equals(date)) {
                for (int i = 0; i < a.getValue().size() - 1; i += 2) {
                    if ((starttime.isAfter(a.getValue().get(i)) & starttime.isBefore(a.getValue().get(i + 1)))
                            || (endtime.isBefore(a.getValue().get(i + 1)) && endtime.isAfter(a.getValue().get(i)))
                            || starttime.equals(a.getValue().get(i)) || endtime.equals(a.getValue().get(i + 1))) {
                        b = false;
                        break;
                    }
                }
            }
        }
        return b;
    }

    int createInts() {
        int num = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            num = Integer.parseInt(bufferedReader.readLine());
            return num;
        } catch (IOException exception) {
            exception.printStackTrace();

        }
        return num;
    }

    public static void chechNotif() {
        try {
            if (notifTrueList.size() == 0) {
                return;
            } else {
                while (!(LocalTime.now().getMinute() == notifTrueList.get(0).getStartTask().getMinute() - 5 && LocalTime.now().getHour()
                        == notifTrueList.get(0).getStartTask().getHour())) {
                    System.out.println("Не настало");
                    Thread.sleep(1000);
                }
            }
            notifTrueList.remove(notifTrueList.get(0));
            System.out.println("Оповещение начала события");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void writeCalendar() {
        try {

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\GBJava\\HomeWorks_java\\GBjavaHomeWorks\\Home-23.02\\Home-23.02\\src\\cal.md", false));

            for (Task task : taskList
            ) {
                bufferedWriter.write("Задача: " + task.getNameTask() + ". ID записи : " + task.getId() + ". Дата записи : " + LocalDate.now() + ". Дедлайн: " + task.getEndTask() + ".Нотификатор: " + task.isNotification() + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void filterlistTask(Prior priority) {
        filterlist.clear();
        for (Task task : taskList
        ) {
            if (task.getType().equals(priority)) {
                System.out.println(task);
            }
        }
    }

    public void getCal() {
        if (taskList.size() == 0) {
            System.out.println("задач нету");
        }
        for (Task task : taskList
        ) {
            System.out.println(task);
        }
    }

    public void getTasks(boolean b) {

        if (b) {
            for (Task task : taskList
            ) {
                if (task instanceof Mytask) {
                    System.out.println(task);
                }
            }
        } else {
            for (Task task : taskList
            ) {
                if (task instanceof Othertask) {
                    System.out.println(task);
                }
            }
        }

    }


    public void repeattask(String name) {
        LocalDate dt1 = null;
        LocalTime lc1 = null;
        LocalTime lc2 = null;
        boolean flag = true;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getNameTask().equals(name)) {
                dt1 = taskList.get(i).getDateCreateTask();
                lc1 = taskList.get(i).getStartTask();
                lc2 = taskList.get(i).getEndTask();
                for (Map.Entry<LocalDate, List<LocalTime>> entry : localDateListMap.entrySet()
                ) {
                    if (entry.getKey().equals(dt1)) {
                            entry.getValue().remove(lc1);
                            entry.getValue().remove(lc2);
                    }
                }
            }
        }
            while (flag) {
                BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                System.out.println(localDateListMap.get(dt1));
                System.out.println("Введите новое время начала");
                lc1 = LocalTime.of(createInts(), createInts());
                System.out.println("Введите продолжительность в минутах");
                int a = 0;
                try {
                    a = Integer.parseInt(bf.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                lc2 = lc1.plusMinutes(a);
                flag = !checklist(dt1, lc1, lc2);

        }
    }
}