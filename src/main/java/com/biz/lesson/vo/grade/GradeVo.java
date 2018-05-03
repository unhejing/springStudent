package com.biz.lesson.vo.grade;

import com.biz.lesson.model.student.Student;

import java.util.Set;

/**
 * @author unhejing
 * @create 2018-04-23 下午4:54
 **/

public class GradeVo {

    private String id;

    private String name;

    private Set<Student> students;

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
