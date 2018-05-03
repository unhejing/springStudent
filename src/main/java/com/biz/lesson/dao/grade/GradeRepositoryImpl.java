package com.biz.lesson.dao.grade;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.grade.Grade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;


public class GradeRepositoryImpl extends CommonJpaRepositoryBean<Grade, String> implements GradeDao {

	@Autowired
	public GradeRepositoryImpl(EntityManager em) {
		super(Grade.class, em);
	}
	
	
}
