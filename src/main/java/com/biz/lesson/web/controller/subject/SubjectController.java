package com.biz.lesson.web.controller.subject;

import com.biz.lesson.business.subject.SubjectService;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.subject.Subject;
import com.biz.lesson.vo.student.StudentVo;
import com.biz.lesson.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author unhejing
 * @create 2018-04-24 下午12:55
 **/
@Controller
@RequestMapping("subject/subject")
public class SubjectController extends BaseController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_SUBJECT_SUBJECT_LIST')")
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView("subject/subject/list");
        List<Subject> subjects = subjectService.list();
        modelAndView.addObject("subjects", subjects);
        return modelAndView;
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_SUBJECT_SUBJECT_ADD')")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("subject/subject/add");
        modelAndView.addObject("cmd", "add");
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_SUBJECT_SUBJECT_ADD')")
    public ModelAndView save_add(StudentVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        Subject subject = new Subject();
        copyProperties(vo, subject);
        subjectService.save(subject);
        return referer(request);
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_SUBJECT_SUBJECT_EDIT')")
    public ModelAndView edit(String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("subject/subject/add");

        Subject subject = subjectService.get(id);

        modelAndView.addObject("cmd", "edit");
        modelAndView.addObject("subject", subject);
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_SUBJECT_SUBJECT_EDIT')")
    public ModelAndView save_edit(StudentVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        Subject subject = subjectService.get(vo.getId());
        BusinessAsserts.exists(subject, vo.getId());

        copyProperties(vo, subject);

        subjectService.save(subject);
        return referer(request);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_SUBJECT_SUBJECT_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("id") String id) {
        try {
            subjectService.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
