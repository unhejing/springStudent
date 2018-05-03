package com.biz.lesson.dao.subject;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.subject.Subject;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectRepository extends CommonJpaRepository<Subject, String>, SubjectDao {


}
