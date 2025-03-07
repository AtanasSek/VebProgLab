package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepositoryJpa extends JpaRepository<Course,Long> {
    //List<Student> findAllStudentsByCourseId(Long courseId);
    Course findByName(String name);
}
