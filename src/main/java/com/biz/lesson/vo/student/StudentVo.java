package com.biz.lesson.vo.student;

import com.biz.lesson.model.grade.Grade;
import com.biz.lesson.model.subject.Subject;

import java.util.Date;
import java.util.List;

/**
 * @author unhejing
 * @create 2018-04-23 下午4:54
 **/

public class StudentVo {

    private String id;

    private String name;

    private String  gender;

    private Integer number;

    private Date birthday;

    private String description;

    private String cmd;

    private Grade grade;

    private List<Subject> subjects;

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}
