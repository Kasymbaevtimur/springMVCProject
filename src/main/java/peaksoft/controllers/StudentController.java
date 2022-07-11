package peaksoft.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Group;
import peaksoft.entities.Student;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

import java.util.List;
@RequestMapping("/students")
@Controller
public class StudentController {


    private final StudentService studentService;

    private final GroupService groupService;

    @Autowired
    public StudentController(StudentService studentService, GroupService groupService) {
        this.studentService = studentService;
        this.groupService = groupService;
    }
    @ModelAttribute("groupList")
    public List<Group> getAllGroup() {
        return groupService.getAllGroups();
    }

    @GetMapping()
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "student/students";
    }

    @GetMapping("/addStudent")
    public String addStudent(Model model) {
        model.addAttribute("course", new Student());
        return "student/addStudent";
    }

    @PostMapping("/saveStudent")
    public String saveStudents(@ModelAttribute("student") Student student) {
        studentService.addStudent(student,student.getGroupId());
        return "redirect:/students";
    }

    @GetMapping("/updateStudent")
    public String updateStudent(@RequestParam("groupId") Long id, Model model) {
        Group group=groupService.getGroupById(id);
        model.addAttribute("group", group);
        return "course/updateStudent";
    }
        @PostMapping("/saveUpdateStudent")
    public String saveUpdateStudent(@ModelAttribute("student") Student student) {
        studentService.updateStudent(student);
        return "redirect:/students";
    }
//
//    @PostMapping("/saveUpdateCourse")
//    public String saveUpdateCourse(@RequestParam("groupId") Long id, @ModelAttribute("group") Group group) {
//        group.setCourses(groupService.getGroupById(id));
//
//    }

    @RequestMapping ("/deleteStudent")
    public String deleteCourse(@RequestParam("groupId") Long id){
        studentService.deleteStudent(studentService.getStudentById(id));
        return "";
    }


}
