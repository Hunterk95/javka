package rest;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
public class User {
    private @Id int user_id;
    private Integer task;

    public int getUserId() {
        return user_id;
    }

    public Integer getTask() {
        return task;
    }
}
