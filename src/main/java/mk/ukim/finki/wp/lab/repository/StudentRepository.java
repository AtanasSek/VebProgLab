package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private List<Student> students = new ArrayList<>();

    public StudentRepository(){
        students.add(new Student("petar.petrov","testPassword","Petar","Petrov"));
        students.add(new Student("mile.milev","testPassword1","Mile","Milev"));
        students.add(new Student("gjorgji.gjorgjiev","testPassword2","Gjorgji","Gjorgji"));
        students.add(new Student("andrej.korjakin","testPassword3","Andrej","Korjakin"));
        students.add(new Student("stefan.stefanovski","testPassword4","Stefan","Stefanovski"));

    }

    public List<Student> findAllStudents(){
        return students;
    }

    public List<Student> findAllByNameOrSurname(String text){

        List<Student> output = new ArrayList<>();

        for (Student student: students) {
            if(student.getName().contains(text) || student.getSurname().contains(text)){
                output.add(student);
            }
        }

        return output;
    }

    //loso
    public Student findByUsername(String username){

        Student output = new Student("NotFound","NotFound","NotFound","NotFound");

        for (Student student:students) {
            if(student.getUsername().equals(username)){
                output = student;
            }
        }

        return output;
    }

    //prvo se dodava student vo lista na studenti ili prvo se dodava student vo course, pa potoa toj odi vo globalnata lista na studenti?
    //za sega e reseno ovaka
    public Student AddStudent(Student student){
        students.add(student);

        return student;
    }
}
