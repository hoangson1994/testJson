package entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Student {

    @Id
    private Long id;
    @Index
    private String name;
    private String rollnumber;
    private int status;

    public Student() {
    }

    public Student(Long id, String name, String rollnumber) {
        this.id = id;
        this.name = name;
        this.rollnumber = rollnumber;
        this.status = 1;
    }

    public Student(String name, String rollnumber) {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.rollnumber = rollnumber;
        this.status = 1;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRollnumber() {
        return rollnumber;
    }

    public void setRollnumber(String rollnumber) {
        this.rollnumber = rollnumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
