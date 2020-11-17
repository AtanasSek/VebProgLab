package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Data
public class Course {

    @Id
    private Long courseId;
    private String name;
    private String description;
    private List<Student> studentsInCourse;

    public Course(Long courseId,String name, String description){
        this.courseId = courseId;
        this.name = name;
        this.description = description;

        studentsInCourse = new ArrayList<>();
    }

    public List<Student> getStudentList(){
        return studentsInCourse;
    }

    //Najverojatno ke ostane neiskoristeno, alternativna klasa vo CourseRepository
    public void AddStudent(Student student){

        studentsInCourse.add(student);

    }
}
