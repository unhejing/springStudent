package com.biz.lesson.dao.score;

import com.biz.lesson.dao.common.CommonJpaRepository;

import com.biz.lesson.model.score.Score;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ScoreRepository extends CommonJpaRepository<Score, String>,ScoreDao,JpaSpecificationExecutor<Score> {

    Score findByStudentIdAndSubjectId(String student_id, String subject_id);

    Score findById(String id);

    @Query(value = "SELECT * FROM stu_student_subject where student_id=:student_id AND subject_id=:subject_id", nativeQuery = true)
    Score findScoreByStudentIdAndSubjectId(@Param("student_id") String student_id, @Param("subject_id") String subject_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM stu_student_subject WHERE id=:id", nativeQuery = true)
    void deleteScoreById(@Param("id") String id);


    List<Score> findByStudentId(String student_id);
}
