package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Student {

    @Id
    private String username;
    private String password;
    private String name;
    private String surname;

    @OneToMany
    private List<Grade> grades;


    //TODO: ?????
    @ManyToMany
    private List<Course> courses;

    public Student(){}

    public Student(String username, String password, String name, String surname){
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public List<Grade> getGrades(){
        return grades;
    }

    public List<Grade> getGrades(String courseName){
        for (Course course:courses) {
            if(course.getName().equals(courseName))
                return course.getGrades();
        }
        return grades;
    }
}
