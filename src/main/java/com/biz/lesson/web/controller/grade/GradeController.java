package com.biz.lesson.web.controller.grade;

import com.biz.lesson.business.grade.GradeService;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.grade.Grade;
import com.biz.lesson.vo.student.StudentVo;
import com.biz.lesson.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author unhejing
 * @create 2018-04-24 下午12:55
 **/
@Controller
@RequestMapping("grade/grade")
public class GradeController extends BaseController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_GRADE_GRADE_LIST')")
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView("grade/grade/list");
        List<Grade> grades = gradeService.list();

        Map<String,Double> gradeSocreMap = new HashMap<String,Double>();

        for(int i = 0; i < grades.size(); i++) {
            Double gradeavgScore = Double.valueOf(0);
            Double sum = 0.0;
            for(int j = 0; j < grades.get(i).getStudents().size(); j++) {
                if(grades.get(i).getStudents().get(j).getAvgScore() != null) {
                    sum += grades.get(i).getStudents().get(j).getAvgScore();
                }
            }
            gradeavgScore = Double.valueOf(sum/grades.get(i).getStudents().size());
            gradeSocreMap.put(grades.get(i).getId(),gradeavgScore);
        }

        modelAndView.addObject("grades", grades);
        modelAndView.addObject("gradeSocreMap", gradeSocreMap);
        return modelAndView;
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_GRADE_GRADE_ADD')")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("grade/grade/add");
        modelAndView.addObject("cmd", "add");
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_GRADE_GRADE_ADD')")
    public ModelAndView save_add(StudentVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        Grade grade = new Grade();
        copyProperties(vo, grade);
        gradeService.save(grade);
        return referer(request);
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_GRADE_GRADE_EDIT')")
    public ModelAndView edit(String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("grade/grade/add");

        Grade grade = gradeService.get(id);

        modelAndView.addObject("cmd", "edit");
        modelAndView.addObject("grade", grade);
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_GRADE_GRADE_EDIT')")
    public ModelAndView save_edit(StudentVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        Grade grade = gradeService.get(vo.getId());
        BusinessAsserts.exists(grade, vo.getId());

        copyProperties(vo, grade);

        gradeService.save(grade);
        return referer(request);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_GRADE_GRADE_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("id") String id) {
        try {
            gradeService.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
