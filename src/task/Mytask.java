package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Mytask extends Task {
    private  String myTaskName;

    public Mytask(int id,String nameTask, String description, boolean notification, LocalDate dateCreateTask, LocalTime startTask, LocalTime endTask,Prior priority) {
        super(id,nameTask, description, notification, dateCreateTask, startTask, endTask,priority);
//       this.myTaskName = mytaskname;
    }
}
