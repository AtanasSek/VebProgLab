package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    public List<Course> listAll(){
        return courseRepository.findAllCourses();
    }

    public List<Student> listStudentsByCourse(Long courseId){
        return courseRepository.findAllStudentsByCourse(courseId);
    }
    public Course addStudentInCourse(String username, Long courseId){
        
        return courseRepository.AddStudentToCourse(studentService.findByUsername(username), courseRepository.findById(courseId));
    }

    public Course findById(Long courseId){
        return courseRepository.findById(courseId);
    }

    @Override
    public void addCourse(Course course) {
        courseRepository.addCourse(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.delete(id);
    }

}
