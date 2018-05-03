package com.biz.lesson.model.subject;

import com.biz.lesson.model.Persistent;
import com.biz.lesson.model.student.Student;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author unhejing
 * @create 2018-04-24 下午1:20
 **/
@Entity
@Table(name = "sub_subject")
public class Subject extends Persistent {

    @Column(length = 40)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "stu_student_subject",
            joinColumns = { @JoinColumn(name = "subject_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id", referencedColumnName = "id") },
            uniqueConstraints = { @UniqueConstraint(columnNames = { "subject_id", "student_id" }) })
    private List<Student> students;

    @Formula("(SELECT COUNT(s.student_id) FROM stu_student_subject s WHERE s.subject_id = id)")
    private Integer selectedPersons;

    @Formula("(SELECT AVG(s.score) FROM stu_student_subject s WHERE s.subject_id = id)")
    private Double selectedAvgScore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getSelectedPersons() {
        return selectedPersons;
    }

    public void setSelectedPersons(Integer selectedPersons) {
        this.selectedPersons = selectedPersons;
    }

    public Double getSelectedAvgScore() {
        return selectedAvgScore;
    }

    public void setSelectedAvgScore(Double selectedAvgScore) {
        this.selectedAvgScore = selectedAvgScore;
    }
}
