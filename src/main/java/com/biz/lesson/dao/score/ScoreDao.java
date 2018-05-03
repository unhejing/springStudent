package com.biz.lesson.dao.score;


import com.biz.lesson.model.score.Score;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

public interface ScoreDao {
    List<Score> check(Score score);
}
