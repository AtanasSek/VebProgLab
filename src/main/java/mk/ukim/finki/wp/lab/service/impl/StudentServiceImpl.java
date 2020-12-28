package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepositoryJpa;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepositoryJpa studentRepository;

    public StudentServiceImpl(StudentRepositoryJpa studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> listAll(){
        return studentRepository.findAll();
    }

    public List<Student> searchByNameOrSurname(String text){
        String[] name = text.split(" ");
        return studentRepository.findAllByNameOrSurname(name[0],name[1]);
    }

    public Student findByUsername(String username){
        return studentRepository.findByUsername(username);
    }

    public Student save(String username, String password, String name, String surname){

        Student student = new Student(username, password, name, surname);
        return studentRepository.save(student);

    }


}
