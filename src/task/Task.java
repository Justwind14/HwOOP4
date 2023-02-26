package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Task  {
    final static Map<LocalDate, List<LocalDate>> localDateListMap = new HashMap<>();
    Prior priority;
    public int getId() {
        return id;
    }

    int id = this.hashCode();
    protected String nameTask;
    protected String description;
    protected boolean notification;
    protected LocalDate dateCreateTask;
    protected LocalTime startTask;
    protected  LocalTime endTask;

    public Prior getType() {
        return priority;
    }

    public Task(int id , String nameTask, String description, boolean notification, LocalDate dateCreateTask, LocalTime startTask, LocalTime endTask, Prior priority) {
        this.nameTask = nameTask;
        this.description = description;
        this.notification = notification;
        this.dateCreateTask = dateCreateTask;
        this.startTask = startTask;
        this.endTask = endTask;
        this.id = id;
        this.priority = priority;

    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public LocalDate getDateCreateTask() {
        return dateCreateTask;
    }

    public void setDateCreateTask(LocalDate dateCreateTask) {
        this.dateCreateTask = dateCreateTask;
    }

    public LocalTime getStartTask() {
        return startTask;
    }

    public void setStartTask(LocalTime startTask) {
        this.startTask = startTask;
    }

    public LocalTime getEndTask() {
        return endTask;
    }

    public void setEndTask(LocalTime endTask) {
        this.endTask = endTask;
    }

    @Override
    public String toString() {
        return "Планы в общем и целом не очень, ну на посмотри :" +

                " Задача = '" + nameTask + '\'' +
                ", Описание = '" + description + '\'' +
                ", Начало = " + startTask +
                ", Конец= " + endTask +
                " Приоритет = " + priority +
                '}';
    }
}
