package rest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task_table")
public class Task {
    private @Id int task_id;
    private String taskBody;

    public int getTaskid() {
        return task_id;
    }

    public String getTaskBody(){
        return taskBody;
    }
}
