package com.biz.lesson.model.grade;

import com.biz.lesson.model.Persistent;
import com.biz.lesson.model.student.Student;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author unhejing
 * @create 2018-04-23 下午2:18
 **/
@Entity
@Table(name = "gra_grade")
public class Grade extends Persistent {


    @Column(length = 40)
    private String name;

    @OneToMany(targetEntity=Student.class,cascade=CascadeType.ALL)
    @JoinColumn(name="grade_id")
    private List<Student> students = new ArrayList<>();

    @Formula("(SELECT COUNT(s.id) FROM stu_student s WHERE s.grade_id = id)")
    private Integer allPersons;

//    @Formula("(SELECT t.grade_id, AVG(t.avgScore) FROM " +
//            "(SELECT s.student_id, AVG(s.score) avgScore, " +
//            "(SELECT g.grade_id FROM stu_student g WHERE g.id=s.student_id) grade_id FROM stu_student_subject s GROUP BY s.student_id) t " +
//            "WHERE t.grade_id=id)")
//    private Double gradeAvgScore;

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

    public Integer getAllPersons() {
        return allPersons;
    }

    public void setAllPersons(Integer allPersons) {
        this.allPersons = allPersons;
    }

//    public Double getGradeAvgScore() {
//        return gradeAvgScore;
//    }
//
//    public void setGradeAvgScore(Double gradeAvgScore) {
//        this.gradeAvgScore = gradeAvgScore;
//    }
}
