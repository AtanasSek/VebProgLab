package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Grade {

    @Id
    @GeneratedValue
    private Long id;

    private Character grade;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;



}
