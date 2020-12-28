package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepositoryJpa extends JpaRepository<Student, String> {
    List<Student> findAllByNameOrSurname(String name , String surname);
    Student findByUsername(String username);
}
