package com.example.food_project.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public boolean storedFile(MultipartFile file);

    Resource loadFileByName(String fileName);
}
