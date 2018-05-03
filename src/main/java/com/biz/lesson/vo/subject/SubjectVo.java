package com.biz.lesson.vo.subject;

import com.biz.lesson.model.student.Student;

import java.util.List;
import java.util.Set;

/**
 * @author unhejing
 * @create 2018-04-23 下午4:54
 **/

public class SubjectVo {

    private String id;

    private String name;

    private List<Student> students;

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
