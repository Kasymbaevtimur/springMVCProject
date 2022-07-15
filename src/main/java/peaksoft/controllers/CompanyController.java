package peaksoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.entities.Company;
import peaksoft.entities.Course;
import peaksoft.entities.Student;
import peaksoft.service.CompanyService;
import peaksoft.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final StudentService studentService;

    @Autowired
    public CompanyController(CompanyService companyService, StudentService studentService) {
        this.companyService = companyService;
        this.studentService = studentService;
    }

    @GetMapping()
    public String getAllCompanies(Model model) {
        List<Company> companies = companyService.getAllCompanies();
        model.addAttribute("companies", companies);
        return "company/companies";
    }

    @GetMapping("/addCompany")
    public String addCompany(Model model) {
        model.addAttribute("company", new Company());
        return "company/addCompany";
    }

    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.addCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/updateCompany")
    public String updateCompany(@RequestParam("companyId") Long id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        return "company/updateCompany";
    }

    @PostMapping("/saveUpdateCompany")
    public String saveUpdateCompany(@ModelAttribute("company") Company company) {
        companyService.updateCompany(company);
        return "redirect:/companies";
    }

    @RequestMapping("/deleteCompany")
    public String deleteCompany(@RequestParam("companyId") Long id) {
        companyService.deleteCompany(companyService.getCompanyById(id));
        return "redirect:/companies";
    }


    @GetMapping("/students/{companyId}")
    public String getStudentsByTeacher(Model model, @PathVariable ("companyId")Long companyId) {
        List<Student> students = studentService.getStudentsByCompany(companyId);
        model.addAttribute("size", students.size());
        model.addAttribute("students",students);
        return "company/getStudents";


    }
}
