package vn.su.jobhunt.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployerRequest {

    private String id;
    
    @NotBlank(message = "{employer.name.notblank}")
    private String name;

    @NotBlank(message = "{employer.website.notblank}")
    private String website;

    @NotBlank(message = "{employer.email.notblank}")
    @Email
    private String email;

    private MultipartFile logo;
}
