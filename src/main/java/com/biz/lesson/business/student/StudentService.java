package com.biz.lesson.business.student;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.StudentRepository;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.util.PageControl;
import com.biz.lesson.vo.student.StudentSearchVo;
import com.biz.lesson.vo.student.StudentVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author unhejing
 * @create 2018-04-23 下午3:12
 **/
@Service
public class StudentService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;


    public List<Student> list() {
        return studentRepository.findAll();
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public Student get(String id) {
        return studentRepository.findOne(id);
    }

    public void delete(String id) {
        studentRepository.delete(id);
    }

//    public Page<Student> searchStudent(StudentSearchVo vo, Pageable pageable) {
//        return studentRepository.searchStudent(vo,pageable);
//
//    }

    public Page<Student> searchStudent(final StudentSearchVo reqVo, PageControl pc) {
        Sort sort = new Sort(Sort.Direction.DESC, "birthday");
        Specification<Student> spec = new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (reqVo.getName() != null) {
                    predicates.add(
                            criteriaBuilder.like(root.get("name"), "%" + reqVo.getName() + "%"));
                }

                if (reqVo.getNumber() != null) {
                    predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(root.get("number"),  reqVo.getNumber()));
                }

                if(reqVo.getStartTime() != null) {
                    predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"),reqVo.getStartTime()));
                }

                if(reqVo.getStartTime() != null) {
                    predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(root.get("birthday"),reqVo.getEndTime()));
                }

                if (predicates.size() > 0) {
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                } else {
                    return null;
                }
            }
        };
        Pageable page = new PageRequest(pc.getCurrentPage() - 1,pc.getPageSize(),sort);
        Page<Student> all = studentRepository.findAll(spec, page);
//        pc.setCount((int) all.getTotalElements());
//        List<Student> content = all.getContent();
//        pc.setList(content);
        return all;
    }
}
