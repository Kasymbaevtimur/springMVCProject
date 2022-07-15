package peaksoft.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CoursesController {

    private final CourseService coursesService;
    private final GroupService groupService;
    private final CompanyService companyService;

    @Autowired
    public CoursesController(CourseService coursesService, GroupService groupService, CompanyService companyService) {
        this.coursesService = coursesService;
        this.groupService = groupService;
        this.companyService = companyService;
    }

    @ModelAttribute("companyList")
    public List<Company> getAllCompany() {
        return companyService.getAllCompanies();
    }

    @GetMapping()
    public String getAllCourses(Model model) {
        List<Course> courses = coursesService.getAllCourses();
        model.addAttribute("courses", courses);
        return "course/courses";
    }

    @GetMapping("/addCourse")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        return "course/addCourse";
    }

    @PostMapping("/saveCourse")
    public String saveCourses(@ModelAttribute("course") Course course) {
        coursesService.addCourse(course, course.getCompanyId());
        return "redirect:/courses";
    }

    @GetMapping("/{id}/updateCourse")
    public String updateCourse(@PathVariable("id") Long id, Model model) {
        Course course = coursesService.getCourseById(id);
        model.addAttribute("courseUpdate", course);
        return "course/updateCourse";
    }


    @PatchMapping("/{id}")
    public String saveUpdateCourse(@PathVariable("id") Long id, @ModelAttribute("courseUpdate") Course course) {
        coursesService.updateCourse(course, id);
        return "redirect:/courses";
    }

    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable("id") Long id) {
        coursesService.deleteCourse(coursesService.getCourseById(id));
        return "redirect:/courses";
    }

    @GetMapping("/group/{courseId}")
    public String getGroupByCourseId(@PathVariable("courseId") Long courseId, Model model) {
        List<Group> groups =coursesService.getGroupByCourse(courseId);
        model.addAttribute("groups", groups);
        return "course/group";
    }
}