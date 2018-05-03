package com.biz.lesson.business.subject;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.subject.SubjectRepository;
import com.biz.lesson.model.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author unhejing
 * @create 2018-04-24 下午12:56
 **/
@Service
public class SubjectService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(SubjectRepository.class);

    @Autowired
    private SubjectRepository subjectRepository;


    public List<Subject> list() {
        return subjectRepository.findAll();
    }

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject get(String id) {
        return subjectRepository.findOne(id);
    }

    public void delete(String id) {
        subjectRepository.delete(id);
    }

}
