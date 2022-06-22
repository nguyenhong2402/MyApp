package vn.su.jobhunt.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import vn.su.jobhunt.model.Employer;

@Repository
public class EmployerRepository {
    private ConcurrentHashMap<String, Employer> employers = new ConcurrentHashMap<>();

    public List<Employer> getAll() {
        return new ArrayList<>(employers.values());
    }

    public Optional<Employer> getById(String employerId) {
        return Optional.ofNullable(employers.get(employerId));
    }

    public void save(Employer employer) {
        employers.put(employer.getId(), employer);
    }

    public void delete(String employerId) {
        employers.remove(employerId);
    }

    public List<Employer> getByName(String employerName) {
        return employers.values().stream()
                .filter(emp -> employerName.equalsIgnoreCase(emp.getName()))
                .collect(Collectors.toList());
    }

}
