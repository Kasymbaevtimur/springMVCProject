package peaksoft.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;
import peaksoft.service.StudentService;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupsController {

    private final StudentService studentService;
    private final CourseService courseService;

    private final GroupService groupService;

    @Autowired
    public GroupsController(StudentService studentService, CourseService courseService, GroupService groupService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.groupService = groupService;
    }

    @ModelAttribute("courseList")
    public List<Course> getAllCourse() {
        return courseService.getAllCourses();
    }



    @GetMapping()
    public String getAllGroups(Model model) {
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "group/groups";
    }

    @GetMapping("/addGroup")
    public String addGroup(Model model) {
        model.addAttribute("group", new Group());
        return "group/addGroup";
    }

    @PostMapping("/saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group) {
        groupService.addGroup(group, group.getCourseId());
        return "redirect:/groups";
    }


    @GetMapping("/{id}/updateGroup")
    public String updateGroup(@PathVariable("id") Long id, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("groupUpdate", group);
        return "group/updateGroup";
    }


    @PatchMapping("/{id}")
    public String saveUpdateGroup(@PathVariable("id") Long id, @ModelAttribute("groupUpdate") Group group) {
        groupService.updateGroup(group, id);
        return "redirect:/groups";
    }

    @DeleteMapping("/delete")
    public String deleteGroup(@RequestParam("id") Long id) {
        groupService.deleteGroup(groupService.getGroupById(id));
        return "redirect:/groups";

    }
    @GetMapping("/search")
    public String search(Model model, String name){
        if (name!=null){
            model.addAttribute("list",studentService.getByName(name));
        }
        else {
            model.addAttribute("list",studentService.getAllStudents());
        }
        return "group/getStudent";
    }
    @GetMapping("/course/{groupId}")
    public String getCourseByGroupId(@PathVariable("groupId") Long groupId, Model model) {
        List<Course> courses = groupService.getCoursesByGroup(groupId);
        model.addAttribute("courses", courses);
        return "group/course";
    }
}