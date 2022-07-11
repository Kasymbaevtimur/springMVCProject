package peaksoft.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.service.CompanyService;
import peaksoft.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CoursesController {

    private final CourseService coursesService;
    private final CompanyService companyService;

    @Autowired
    public CoursesController(CourseService coursesService, CompanyService companyService) {
        this.coursesService = coursesService;
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

    @GetMapping("/updateCourse")
    public String updateCourse(@RequestParam("companyId") Long id, Model model) {
        Course course = coursesService.getCourseById(id);
        model.addAttribute("course", course);
        return "course/updateCourse";
    }
//    @PostMapping("/saveUpdateCourse")
//    public String saveUpdateCourse(@ModelAttribute("course") Course course) {
//        coursesService.updateCourse(course);
//        return "redirect:/courses";
//    }
//
    @PostMapping("/saveUpdateCourse")
    public String saveUpdateCourse(@RequestParam("companyId") Long id, @ModelAttribute("course") Course course) {
        course.setCompany(companyService.getCompanyById(id));
        coursesService.updateCourse(course);
        return "redirect:/courses";
    }

    @DeleteMapping("/deleteCourse")
    public String deleteCourse(@RequestParam("courseId") Long id, @RequestParam("companyId") Long id2){
        coursesService.deleteCourse(coursesService.getCourseById(id));
        return "";
    }
}
