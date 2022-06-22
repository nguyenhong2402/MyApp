package vn.su.jobhunt.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import vn.su.jobhunt.exception.StorageException;

@Service
public class StorageService {
    @Value("${upload.path}")
    private String path;

    // Lưu file vào path
    public String saveFile(MultipartFile file, String employerId) {
        if (file.isEmpty()) {
            throw new StorageException("File không hợp lệ");
        }
        String logoFileName = employerId + "." + getFileExtension(file.getOriginalFilename()); // Thay tên của file photo
        String logoPath = path + logoFileName;
        try (var is = file.getInputStream()) {
            Files.copy(is, Paths.get(logoPath), StandardCopyOption.REPLACE_EXISTING);   // Copy vào đường dẫn path, thay thế nếu trùng tên
        } catch (IOException e) {
            //var msg = String.format("Failed to store file %s", logoPath);
            throw new StorageException("File không hợp lệ", new String[] { logoPath });
        }
        return logoFileName;
    }
    
    // Cắt phần đuôi của tên file
    private String getFileExtension(String fileName) {
        int postOfDot = fileName.lastIndexOf(".");
        if (postOfDot < 0) {
          return null;
        }
        return fileName.substring(postOfDot + 1);
      }
}
