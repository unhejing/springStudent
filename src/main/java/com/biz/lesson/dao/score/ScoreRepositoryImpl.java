package com.biz.lesson.dao.score;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.dao.grade.GradeDao;
import com.biz.lesson.model.grade.Grade;
import com.biz.lesson.model.score.Score;
import com.biz.lesson.model.user.AccessLogPo;
import com.biz.lesson.vo.user.AccessLogSearchVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;

public class ScoreRepositoryImpl extends CommonJpaRepositoryBean<Score, String> implements ScoreDao {

	@Autowired
	public ScoreRepositoryImpl(EntityManager em) {
		super(Score.class, em);
	}

	public Specification<Score> checkStudentAndSubject(final Score score) {
		return (root, query, cb) -> {
			List<Predicate> predicates = Lists.newArrayList();

			if (StringUtils.isNotBlank(score.getStudent().getId())) {
				predicates.add(cb.equal(root.get("student_id"), score.getStudent().getId()));
			}

			if (StringUtils.isNotBlank(score.getSubject().getId())) {
				predicates.add(cb.equal(root.get("subject_id"), score.getSubject().getId()));
			}

			// 将所有条件用 and 联合起来
			if (predicates.size() > 0) {
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			} else {
				return null;
			}
		};
	}

	public List<Score> check(Score score){
		return super.findAll(checkStudentAndSubject(score));
	}
	
}
