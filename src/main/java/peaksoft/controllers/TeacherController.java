package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Course;
import peaksoft.entities.Teacher;
import peaksoft.service.CourseService;
import peaksoft.service.TeacherService;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    private final CourseService courseService;

    @Autowired
    public TeacherController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }

    @ModelAttribute("courseList")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping()
    public String getAllTeacher(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teacher/teachers";
    }

    @GetMapping("/addTeacher")
    public String addCourse(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher/addTeacher";
    }

    @PostMapping("/saveTeacher")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.addTeacher(teacher,teacher.getCourseId());
        return "redirect:/teachers";
    }

    @GetMapping("/updateTeacher")
    public String updateCourse(@RequestParam("courseId") Long id, Model model) {
       Teacher teacher= teacherService.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "teacher/updateTeacher";
    }
    @PostMapping("/saveUpdateTeacher")
    public String saveUpdateCourse(@ModelAttribute("teacher") Teacher teacher) {
        teacherService.updateTeacher(teacher);
        return "redirect:/teachers";
    }
//
//    @PostMapping("/saveUpdateCourse")
//    public String saveUpdateCourse(@RequestParam("companyId") Long id, @ModelAttribute("course") Course course) {
//        course.setCompany(companyService.getCompanyById(id));
//        coursesService.updateCourse(course);
//        return "redirect:/courses";
//    }

    @RequestMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam("teacherId") Long id, @RequestParam("courseId") Long id2) {
        teacherService.deleteTeacher(teacherService.getTeacherById(id));
        return "redirect:/teachers";
    }
}

