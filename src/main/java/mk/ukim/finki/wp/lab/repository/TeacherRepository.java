package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TeacherRepository {

    private List<Teacher> teacherList = new ArrayList<>();

    public TeacherRepository(){

        teacherList.add(new Teacher(1L,"Professor","Professorski"));
        teacherList.add(new Teacher(2L,"Teacher","Teacherski"));
        teacherList.add(new Teacher(3L,"Generic","Genericski"));
        teacherList.add(new Teacher(4L,"Default","Defaultski"));
        teacherList.add(new Teacher(5L,"Placeholder","Placeholderski"));
    }

    public List<Teacher> findAll(){
        return teacherList;
    }

    public Teacher findById(Long id) {
        for (Teacher teacher : teacherList) {
            if(teacher.getId().equals(id))
                return teacher;
        }
        return null;
    }
}
