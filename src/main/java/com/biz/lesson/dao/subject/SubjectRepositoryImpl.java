package com.biz.lesson.dao.subject;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;


public class SubjectRepositoryImpl extends CommonJpaRepositoryBean<Subject, String> implements SubjectDao {

	@Autowired
	public SubjectRepositoryImpl(EntityManager em) {
		super(Subject.class, em);
	}
	
	
}
