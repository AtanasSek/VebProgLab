package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public Teacher(){}

    public Teacher(String name, String surname) {
        this.name = name;
        this.surname = surname;
        courses = new ArrayList<>();
    }

    public Teacher(Long id , String name , String surname){
        this.id = id;
        this.name = name;
        this.surname = surname;
        courses = new ArrayList<>();
    }


}
