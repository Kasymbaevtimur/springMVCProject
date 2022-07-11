package peaksoft.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;

import java.util.List;

@Controller
@RequestMapping("/groups")
public class GroupsController {

    private final CourseService courseService;

    private final GroupService groupService;

    @Autowired
    public GroupsController(CourseService courseService, GroupService groupService) {
        this.courseService = courseService;
        this.groupService = groupService;
    }

    @ModelAttribute("courseList")
    public List<Course> getAllCourse() {
        return courseService.getAllCourses();
    }

    @GetMapping()
    public String getGroupsPage(Model model) {
        List<Group> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "group/groups";
    }

    @GetMapping("/addGroup")
    public String addCourse(Model model) {
        model.addAttribute("group", new Group());
        return "group/addGroup";
    }

    @PostMapping("saveGroup")
    public String saveGroup(@ModelAttribute("group") Group group) {
        groupService.addGroup(group, group.getCourseId());
        return "redirect:/groups";
    }


    @GetMapping("/updateGroup")
    public String updateGroup(@RequestParam("courseId") Long id, Model model) {
        Group group = groupService.getGroupById(id);
        model.addAttribute("group", group);
        return "group/updateGroup";
    }

    @PatchMapping("/saveUpdateGroup")
    public String saveUpdateGroup(@ModelAttribute("group") Long id, Group group) {
        groupService.updateGroup(group, id);
        return "redirect:/groups";
    }

//@PostMapping("/saveUpdateGroup")
//public String saveUpdateGroup(@RequestParam("courseId") Long id, @ModelAttribute("group") Group group) {
//    group.setCourses((List<Course>) courseService.getCourseById(id));
//    groupService.updateGroup(group);
//    return "redirect:/courses";
//}

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("groupUpdate") Group group,
                         @PathVariable("id") long id) {
        groupService.updateGroup(group, id);
        return "redirect:/groups";
    }

    @DeleteMapping("/deleteGroup")
    public String deleteCourse(@RequestParam("groupId") Long id, @RequestParam("courseId") Long id2) {
        groupService.deleteGroup(groupService.getGroupById(id));
        return "";

    }
}