package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.vo.student.StudentSearchVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;

public class StudnetRepositoryImpl extends CommonJpaRepositoryBean<Student, String> implements StudentDao{

	@Autowired
	public StudnetRepositoryImpl(EntityManager em) {
		super(Student.class, em);
	}

//	public Specification<Student> buildSearchSpecification(final StudentSearchVo reqVo) {
//		return (root, query, cb) -> {
//			List<Predicate> predicates = Lists.newArrayList();
//
//			if (StringUtils.isNotBlank(reqVo.getName())) {
//				predicates.add(cb.like(root.get("name"), "%" + reqVo.getName() + "%"));
//			}
//
//			if (reqVo.getNumber() != 0 && reqVo.getNumber() != null) {
//				predicates.add(cb.like(root.get("number"), "%" + reqVo.getNumber() + "%"));
//			}
//
//			if (reqVo.getStartTime() != null) {
//				predicates.add(cb.greaterThan(root.get("birthday"), reqVo.getStartTime()));
//			}
//
//			if (reqVo.getEndTime() != null) {
//				predicates.add(cb.lessThan(root.get("birthday"), reqVo.getEndTime()));
//			}
//			// 将所有条件用 and 联合起来
//			if (predicates.size() > 0) {
//				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
//			} else {
//				return null;
//			}
//		};
//	}
//
//	public Page<Student> searchStudent(StudentSearchVo reqVo, Pageable pageable) {
//		return super.findAll(buildSearchSpecification(reqVo),pageable);
//	}



	
}
