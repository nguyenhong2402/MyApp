package vn.su.jobhunt.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import vn.su.jobhunt.exception.ResourceNotFoundException;
import vn.su.jobhunt.exception.StorageException;

@ControllerAdvice
public class GlobalController {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleStorageFileNotFound(MaxUploadSizeExceededException e, Model model) {
        model.addAttribute("errorMessage", "File is too large. Detail: " + e.getMessage());
        return "failure";
    }

    @ExceptionHandler(StorageException.class)
    public String handleStorageFileNotFound(StorageException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "failure";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(ResourceNotFoundException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "failure";
    }
}
