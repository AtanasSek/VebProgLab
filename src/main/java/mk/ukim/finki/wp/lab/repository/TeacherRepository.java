package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepository {

    private List<Teacher> teacherList = new ArrayList<>();

    public TeacherRepository(){

        teacherList.add(new Teacher("Professor","Professorski"));
        teacherList.add(new Teacher("Teacher","Teacherski"));
        teacherList.add(new Teacher("Generic","Genericski"));
        teacherList.add(new Teacher("Default","Defaultski"));
        teacherList.add(new Teacher("Placeholder","Placeholderski"));
    }

    public List<Teacher> findAll(){
        return teacherList;
    }

}
