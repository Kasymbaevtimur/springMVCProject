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
        teacherService.addTeacher(teacher, teacher.getCourseId());
        return "redirect:/teachers";
    }

    @GetMapping("/{id}/updateTeacher")
    public String updateCourse(@PathVariable("id") Long id, Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        model.addAttribute("teacherUpdate", teacher);
        return "teacher/updateTeacher";
    }

    @PatchMapping("/{id}")
    public String saveUpdateCourse(@PathVariable("id") Long id, @ModelAttribute("teacherUpdate") Teacher teacher) {
        teacherService.updateTeacher(teacher, id);
        return "redirect:/teachers";
    }

    @DeleteMapping()
    public String deleteTeacher(@RequestParam("id") Long id) {
        teacherService.deleteTeacher(teacherService.getTeacherById(id));
        return "redirect:/teachers";
    }
}

