package com.biz.lesson.vo.student;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.sql.Date;

/**
 * @author unhejing
 * @create 2018-05-01 下午4:30
 **/

public class StudentSearchVo {

    private Integer number;
    private String name;
    private Date startTime;
    private Date endTime;
    @Min(1)
    private Integer page = 1;

    @Max(100)
    private Integer pageSize = 20;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
