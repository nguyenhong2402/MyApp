package vn.su.jobhunt.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import vn.su.jobhunt.dto.EmployerRequest;
import vn.su.jobhunt.model.Employer;

public interface EmployerService {

    List<Employer> getAllEmployer();

    Employer getEmployerById(String employerId);

    Employer addEmployer(EmployerRequest employerRequest);

    void edit(EmployerRequest employerRequest);

    void delete(String employerId);
    
    EmployerRequest getByIdEmployerRequest(Employer employerById);
}
