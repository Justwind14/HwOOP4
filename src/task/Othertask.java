package task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Othertask extends Task {
    public Othertask(int id, String nameTask, String description, boolean notification, LocalDate dateCreateTask, LocalTime startTask, LocalTime endTask, Prior priority) {
        super(id, nameTask, description, notification, dateCreateTask, startTask, endTask, priority);
    }
}
