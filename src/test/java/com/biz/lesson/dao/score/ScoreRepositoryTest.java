package com.biz.lesson.dao.score;

import com.biz.lesson.model.score.Score;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application-content.xml")
public class ScoreRepositoryTest {

    @Autowired
    private ScoreRepository scoreRepository;




    @Test
    public void findByStudentIdAndSubjectIdTest() throws Exception {
        Score score = scoreRepository.findByStudentIdAndSubjectId("8aff8a5462f6b3790162f6c524760000", "8aff8a5462ffa64a0162ffa8475d0001");
        System.out.println("成功获取值。。。");
        System.out.println(score);
        System.out.println("成功获取值后。。。");
    }

    @Test
    public void findById() throws Exception {
        Score score = scoreRepository.findById("8aff8a5462f78bd80162f7a3dfab0001");
        System.out.println(score);
    }

    @Test
    public void findByStudentIdAndSubjectId() throws Exception {
        Score score = scoreRepository.findScoreByStudentIdAndSubjectId("8aff8a5462f6b3790162f6c524760000","8aff8a5462ffa64a0162ffa8475d0001");
        System.out.println(score);
    }

    @Test
    public void findAllTest() throws Exception {
        List<Score> scoreList = scoreRepository.findAll();
//        Assert.assertNotNull(scoreList);
        System.out.println("成功。。。");
        System.out.println(scoreList);
    }

    @Test
    public void findScoreByIdTest() throws Exception {
        scoreRepository.deleteScoreById("8aff8a5462ffa64a0162ffb39c0d0009");
    }

    @Test
    public void findByStudentId() throws Exception {
        scoreRepository.findByStudentId("8aff8a5462f6b3790162f6c524760000");
    }

}