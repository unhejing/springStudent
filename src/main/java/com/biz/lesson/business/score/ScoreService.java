package com.biz.lesson.business.score;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.grade.GradeRepository;
import com.biz.lesson.dao.score.ScoreRepository;
import com.biz.lesson.model.grade.Grade;
import com.biz.lesson.model.score.Score;
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
public class ScoreService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(ScoreRepository.class);

    @Autowired
    private ScoreRepository scoreRepository;


    public List<Score> list() {
        return scoreRepository.findAll();
    }

    public Score save(Score score) {
        return scoreRepository.save(score);
    }

    public Score get(String id) {
        return scoreRepository.findOne(id);
    }

    public void delete(String id) {
        scoreRepository.delete(id);
    }

    public void deleteScoreById(String id) {
        scoreRepository.deleteScoreById(id);
    }

    public void delete(Score score){
        scoreRepository.delete(score);
    }


    public Score findById(String id) {
        return scoreRepository.findById(id);
    }

    public Score findByStudentIdAndSubjectId(String student_id,String subject_id) {
        return scoreRepository.findByStudentIdAndSubjectId(student_id,subject_id);
    }

    public List<Score> findByStudentId(String student_id) {
        return scoreRepository.findByStudentId(student_id);
    }


//    public Score check(String student_id, String subject_id) {
//        return scoreRepository.findByStudentIdAndSubjectId(student_id,subject_id);
//    }
}
