package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepositoryJpa;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepositoryJpa courseRepository;
    private final StudentService studentService;

    public CourseServiceImpl(CourseRepositoryJpa courseRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    public List<Course> listAll(){
        return courseRepository.findAll();
    }

    public List<Student> listStudentsByCourse(Long courseId){
        Course course = courseRepository.getOne(courseId);
        return course.getStudentsInCourse();
    }

    @Transactional
    public Course addStudentInCourse(String username, Long courseId){
        Course course = courseRepository.getOne(courseId);
        course.AddStudent(studentService.findByUsername(username));
        return courseRepository.save(course);
    }

    public Course findById(Long courseId){
        Course course = courseRepository.getOne(courseId);
        return course;
    }

    @Override
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.getOne(id);
        courseRepository.delete(course);
    }

    @Override
    public void editCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public Boolean checkIfCourseExists(String name) {
        Course course = courseRepository.findByName(name);
        if(course != null)
            return true;
        else return false;
        //return courseRepository.checkIfCourseExists(name);
    }

}
