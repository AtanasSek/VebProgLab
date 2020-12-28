package mk.ukim.finki.wp.lab.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String name;
    private String description;

    @ManyToMany
    private List<Student> studentsInCourse;

    @ManyToOne
    private Teacher teacher;

    @OneToMany
    private List<Grade> grades;

    private Type type;

    public Course(){}

    public Course(Long courseId,String name, String description){
        this.courseId = courseId;
        this.name = name;
        this.description = description;

        studentsInCourse = new ArrayList<>();
    }

    public Course(String name, String description, Teacher teacher){
        this.name = name;
        this.description = description;
        this.teacher = teacher;
    }

    public List<Student> getStudentList(){
        return studentsInCourse;
    }

    //Najverojatno ke ostane neiskoristeno, alternativna klasa vo CourseRepository
    public void AddStudent(Student student){

        studentsInCourse.add(student);

    }
}
