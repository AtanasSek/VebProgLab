package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private List<Course> courses;

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
