package com.biz.lesson.business.grade;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.grade.GradeRepository;
import com.biz.lesson.model.grade.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author unhejing
 * @create 2018-04-24 下午12:56
 **/
@Service
public class GradeService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(GradeRepository.class);

    @Autowired
    private GradeRepository gradeRepository;


    public List<Grade> list() {
        return gradeRepository.findAll();
    }

    public Grade save(Grade classes) {
        return gradeRepository.save(classes);
    }

    public Grade get(String id) {
        return gradeRepository.findOne(id);
    }

    public void delete(String id) {
        gradeRepository.delete(id);
    }
}
