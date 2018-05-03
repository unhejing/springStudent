package com.biz.lesson.model.student;

import com.biz.lesson.model.Persistent;
import com.biz.lesson.model.grade.Grade;
import com.biz.lesson.model.subject.Subject;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author unhejing
 * @create 2018-04-23 下午2:18
 **/
@Entity
@Table(name = "stu_student")
public class Student extends Persistent {


    @Column
    private Integer number;

    @Column(length = 40)
    private String name;

    @Column(length = 1)
    private String gender;

    @Column
    private Date birthday;

    @Column(length = 255)
    private String description;


    @ManyToOne(targetEntity = Grade.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "grade_id")
    private Grade grade;

//    @Formula("(select count(subject_id) from stu_student_subject where student_id = id)")
//    private Integer selectedSubject;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "stu_student_subject",
            joinColumns = { @JoinColumn(name = "student_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "subject_id", referencedColumnName = "id") },
            uniqueConstraints = { @UniqueConstraint(columnNames = { "student_id", "subject_id" }) })
    private List<Subject> subjects;

    @Formula("(SELECT AVG(s.score) FROM stu_student_subject s WHERE s.student_id = id)")
    private Double avgScore;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }


    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
