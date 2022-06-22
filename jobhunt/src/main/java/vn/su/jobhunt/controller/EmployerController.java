package vn.su.jobhunt.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.su.jobhunt.dto.EmployerRequest;

import vn.su.jobhunt.service.EmployerService;

@Controller
@RequestMapping("/employer")
public class EmployerController {

    /* Các chức năng lần lượt là: Lấy danh sách, Tìm theo id, Thêm , Sửa, Xoá Employer */
    @Autowired
    EmployerService employerService;

    @GetMapping
    public String getListAllEmployer(Model model) {
        model.addAttribute("employers", employerService.getAllEmployer());
        return "employers";
    }

    @GetMapping("/{id}")
    public String showEmployerById(@PathVariable("id") String employerId, Model model) {
        model.addAttribute("employer", employerService.getEmployerById(employerId));
        return "employer";
    }

    @GetMapping("/add")
    public String formEmployer(Model model) {
        model.addAttribute("employerForm", new EmployerRequest());
        return "employerForm";
    }
    @PostMapping(value = "/add", consumes = {"multipart/form-data"})
    public String addEmployer(@Valid @ModelAttribute("employerForm") EmployerRequest employerRequest, BindingResult result,
            Model model) {
        if (employerRequest.getLogo().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("employerForm", "logo", "File ảnh không hợp lệ"));
        }
        if (result.hasErrors()) {
            return "employerForm";
        }
        employerService.addEmployer(employerRequest);
        return "redirect:/employer";
    }

    @GetMapping("/edit/{id}")
    public String showEmployerFormByEdit(@PathVariable("id") String employerId, Model model) {
        
            model.addAttribute("employerForm", employerService.getByIdEmployerRequest(
                    employerService.getEmployerById(employerId)));    
        
        return "edit-employer";
    }

    @PostMapping("/edit/{id}")
    public String editEmployer(@Valid @ModelAttribute("employerForm") EmployerRequest employerRequest,
            @PathVariable("id") String employerId, BindingResult result, Model model) {
        if (employerRequest.getLogo().getOriginalFilename().isEmpty()) {
            result.addError(new FieldError("employerForm", "logo", "Ảnh ko hợp lệ"));
        }

        if (result.hasErrors()) {
            return "edit-employer";
        }
        employerRequest.setId(employerId);
        employerService.edit(employerRequest);
        return "redirect:/employer";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployerById(@PathVariable("id") String employerId) {
        employerService.delete(employerId);
        return "redirect:/employer";
    }
}
