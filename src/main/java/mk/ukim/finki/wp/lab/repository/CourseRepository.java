package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    List<Course> courses = new ArrayList<>();

    public CourseRepository(){
        courses.add(new Course(1L,"Веб програмирање","testCourseDescription"));
        courses.add(new Course(2L,"Оперативни системи","testCourseDescription1"));
        courses.add(new Course(3L,"Електронска и мобилна трговија","testCourseDescription2"));
        courses.add(new Course(4L,"Компјутерски мрежи","testCourseDescription3"));
        courses.add(new Course(5L,"Интернет технологии","testCourseDescription4"));
    }

    public List<Course> findAllCourses(){
        return courses;
    }

    public Course findById(Long courseId){
        for (Course course:courses) {
            if(course.getCourseId() == courseId){
                return course;
            }
        }

        //Error return za sekoj slucaj, plus bara return nadvor od If-ot.
        Course errorReturn = new Course(0L, "Course not found" , "Course not found");
        return errorReturn;
    }

    public List<Student> findAllStudentsByCourse(Long courseId){

        List<Student> courseStudents = new ArrayList<>();
        for (Student student: findById(courseId).getStudentsInCourse()) { // Mrzlivo resenie so reimplementacija na findById vo istata klasa, ez
            courseStudents.add(student);
        }

        return courseStudents;
    }

    public Course AddStudentToCourse(Student student,Course course){
        course.AddStudent(student);

        return course;
    }

    public void addCourse(Course course){
        courses.add(course);
    }


    public void delete(Long id) {
        courses.remove(findById(id));
    }

    public void edit(Course course) {
        for (Course c:courses) {
            if(c.getCourseId().equals(course.getCourseId())){
                courses.set(courses.indexOf(c),course);
            }
        }
        System.out.println(course);
    }

    public Boolean checkIfCourseExists(String name) {
        for(Course course: courses){
            if(name.equals(course.getName())){
                return true;
            }
        }
        return false;
    }
}
