package vn.su.jobhunt.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {
    /* Tạo Bean modelmapper để chuyển đổi dữ liệu nhận về từ người dùng */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
