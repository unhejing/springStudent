package com.biz.lesson.business.score;

import com.biz.lesson.dao.score.ScoreRepository;
import com.biz.lesson.model.score.Score;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ScoreServiceTest {

    @Autowired
    private ScoreService scoreService;

    @Test
    public void list() throws Exception {
        List<Score> scores = scoreService.list();
    }

}