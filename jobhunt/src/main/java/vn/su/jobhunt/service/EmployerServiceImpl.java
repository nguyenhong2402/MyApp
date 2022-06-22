package vn.su.jobhunt.service;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.su.jobhunt.dto.EmployerRequest;
import vn.su.jobhunt.exception.ResourceNotFoundException;
import vn.su.jobhunt.model.Employer;
import vn.su.jobhunt.repository.EmployerRepository;

@Service
public class EmployerServiceImpl implements EmployerService {

    @Autowired
    private StorageService storageService;

    @Autowired
    private EmployerRepository employerRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<Employer> getAllEmployer() {
        return employerRepo.getAll();
    }

    @Override
    public Employer getEmployerById(String employerId) {
        return employerRepo.getById(employerId)
                .orElseThrow(() -> new ResourceNotFoundException("employer.id.notExisted", null));
    }

    @Override
    public Employer addEmployer(EmployerRequest employerRequest) {
        Employer employer = modelMapper.map(employerRequest, Employer.class); // Dùng modelMapper để chuyển dữ liệu trả về từ client thành dữ liệu lưu trong DB
        employer.setId(UUID.randomUUID().toString());
        String logoPath = storageService.saveFile(employerRequest.getLogo(), employer.getId());
        employer.setLogoPath(logoPath);
        employerRepo.save(employer);
        return employer;
    }


    @Override
    public void edit(EmployerRequest employerRequest) {
        Employer editEmployer = modelMapper.map(employerRequest, Employer.class);
        String logoPath = storageService.saveFile(employerRequest.getLogo(), editEmployer.getId());
        editEmployer.setLogoPath(logoPath);
        employerRepo.save(editEmployer);
        
    }

    @Override
    public void delete(String employerId) {
        getEmployerById(employerId);
        employerRepo.delete(employerId);
        
    }

    @Override
    public EmployerRequest getByIdEmployerRequest(Employer employerById) {
        EmployerRequest employerRequest = modelMapper.map(employerById, EmployerRequest.class);

        return employerRequest;
    }
    
}
