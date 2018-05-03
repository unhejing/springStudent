package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.grade.GradeService;
import com.biz.lesson.business.score.ScoreService;
import com.biz.lesson.business.student.StudentService;
import com.biz.lesson.business.subject.SubjectService;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.grade.Grade;
import com.biz.lesson.model.score.Score;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.subject.Subject;
import com.biz.lesson.util.PageControl;
import com.biz.lesson.vo.student.StudentSearchVo;
import com.biz.lesson.vo.student.StudentVo;
import com.biz.lesson.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.*;

/**
 * @author unhejing
 * @create 2018-04-23 下午3:18
 **/
@Controller
@RequestMapping("student/student")
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private ScoreService scoreService;

//    @RequestMapping("/list")
//    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_LIST')")
//    public ModelAndView list() throws Exception {
//        ModelAndView modelAndView = new ModelAndView("student/student/list");
//        List<Student> students = studentService.list();
//        modelAndView.addObject("students", students);
//
//        return modelAndView;
//    }

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_LIST')")
    public ModelAndView list(StudentSearchVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        error(result, request);

        ModelAndView modelAndView = new ModelAndView("student/student/list");

        PageControl pc = new PageControl(request, 5);
        modelAndView.addObject("pageControl", pc);

        Page<Student> students = studentService.searchStudent(vo, pc);

        modelAndView.addObject("students", students);
        logger.debug("学生信息："+students);
        modelAndView.addObject("vo", vo);
        return modelAndView;
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_ADD')")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/add");
        modelAndView.addObject("cmd", "add");
        List<Grade> grades = gradeService.list();
        modelAndView.addObject("grades", grades);
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_ADD')")
    public ModelAndView save_add(StudentVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        Student student = new Student();
        if (request.getParameter("birthday") != null && !request.getParameter("birthday").equals("")) {
            vo.setBirthday(Date.valueOf(request.getParameter("birthday")));
        }

        if (request.getParameter("grade_id") != null && !request.getParameter("grade_id").equals("")) {
            Grade grade = gradeService.get(request.getParameter("grade_id"));
            vo.setGrade(grade);
        }
        copyProperties(vo, student);
        studentService.save(student);
        return referer(request);
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_EDIT')")
    public ModelAndView edit(String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/add");

        Student student = studentService.get(id);

        List<Grade> grades = gradeService.list();
        modelAndView.addObject("grades", grades);
        modelAndView.addObject("cmd", "edit");
        modelAndView.addObject("student", student);
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_EDIT')")
    public ModelAndView save_edit(StudentVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        Student student = studentService.get(vo.getId());
        if (request.getParameter("birthday") != null && !request.getParameter("birthday").equals("")) {
            vo.setBirthday(Date.valueOf(request.getParameter("birthday")));
        }
        if (request.getParameter("grade_id") != null && !request.getParameter("grade_id").equals("")) {
            Grade grade = gradeService.get(request.getParameter("grade_id"));
            vo.setGrade(grade);
        }
        BusinessAsserts.exists(student, vo.getId());

        copyProperties(vo, student);

        studentService.save(student);
        return referer(request);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("id") String id) {
        try {
            studentService.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @RequestMapping("/select")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_SELECT')")
    public ModelAndView select(String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/select");
        modelAndView.addObject("cmd", "select");

        Student student = new Student();
        student = studentService.get(id);
        List<Subject> subjects = subjectService.list();
        modelAndView.addObject("student", student);
        modelAndView.addObject("subjects", subjects);
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_select")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_SELECT')")
    public ModelAndView save_select(StudentVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        Student student = studentService.get(vo.getId());
        System.out.println(student);
        String[] subjectIds = {};
        if (request.getParameterValues("duallistbox_demo1[]") != null && request.getParameterValues("duallistbox_demo1[]").length != 0) {
            subjectIds = request.getParameterValues("duallistbox_demo1[]");
        }

        //根据已选课程选出未选课程
        //1.查询所有课程表
        List<Subject> allSubjects = subjectService.list();
        //list转换成set
        Set<Subject> allSubjectSet = new HashSet(allSubjects);
        Set<Subject> allSubjectSet2 = new HashSet(allSubjects);
        //2.根据已选课程选出未选课程
        for(int j = 0; j < subjectIds.length; j++) {
            //如果所选课程id包含在课表里面就删除它，获取出未选课程的id集合
            Iterator<Subject> it = allSubjectSet.iterator();
            while(it.hasNext()) {
                Subject subjectTemp = new Subject();
                subjectTemp = it.next();
                if(subjectTemp.getId().equals(subjectIds[j])) {
                    allSubjectSet2.remove(subjectTemp);
                }
            }
        }

        List<Subject> unselectedSubject = new ArrayList(allSubjectSet2);
        List<Subject> subjects = new ArrayList<>();
        Subject subject = new Subject();
        for (int i = 0; i < subjectIds.length; i++) {
            Score score = new Score();
            System.out.println("subject");
            subject = subjectService.get(subjectIds[i]);
            score.setSubject(subject);
            score.setStudent(student);
            //检查该课程是否已经选择，若未选择，则添加进入选课表里面。

            if(scoreService.findByStudentIdAndSubjectId(student.getId(),subject.getId()) == null){
                scoreService.save(score);
            }
        }
        //判断未选课程里面如果和课程表里面有一样的，就删除它，说明学生有退课
        for(int n = 0; n < unselectedSubject.size(); n++) {
            Score score1 = scoreService.findByStudentIdAndSubjectId(student.getId(),unselectedSubject.get(n).getId());
            if( score1 != null) {
                String id = score1.getId();
                scoreService.deleteScoreById(score1.getId());
            }
        }
        return referer(request);
    }

    /**
     * 加载录入分数页面
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/enter")
    @PreAuthorize("hasAuthority('OPT_STUDENT_SCORE_ENTER')")
    public ModelAndView enter(String id,HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/score/enter");
        modelAndView.addObject("cmd", "enter");

        List<Score> scores = scoreService.findByStudentId(id);
        modelAndView.addObject("scores", scores);
        modelAndView.addObject("id", id);
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_enter")
    @PreAuthorize("hasAuthority('OPT_STUDENT_SCORE_ENTER')")
    public ModelAndView save_enter(String id, HttpServletRequest request) throws Exception {

        List<Score> scores = scoreService.findByStudentId(id);
        for(int i = 0; i < scores.size(); i++) {
            if(StringUtils.isNotBlank(request.getParameter(scores.get(i).getId()))) {
                scores.get(i).setScore(Integer.valueOf(request.getParameter(scores.get(i).getId())));
                scoreService.save(scores.get(i));
            }
        }
        return referer(request);
    }

//    @RequestMapping("/search")
//    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_SEARCH')")
//    public ModelAndView search(StudentSearchVo vo, BindingResult result, HttpServletRequest request) throws Exception {
//        error(result, request);
//
//        ModelAndView modelAndView = new ModelAndView("student/student/list");
//
//        PageControl pc = new PageControl(request, 200);
//        modelAndView.addObject("pageControl", pc);
//
//        List<Student> students = studentService.searchStudent(vo, pc);
//
//        modelAndView.addObject("students", students);
//        logger.debug("学生信息："+students);
//        modelAndView.addObject("vo", vo);
//        return modelAndView;
//    }


}
