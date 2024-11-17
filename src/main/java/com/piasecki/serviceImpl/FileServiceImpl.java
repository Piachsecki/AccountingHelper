package com.piasecki.serviceImpl;

import com.piasecki.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public void uploadFile(MultipartFile file) {
        try {
            if(file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }
            Path rootDir = Paths.get("src", "main", "resources", "static").toAbsolutePath().normalize();
            Path destination = rootDir.resolve(file.getOriginalFilename()).normalize().toAbsolutePath();
            Files.copy(file.getInputStream(), destination);
        } catch(IOException e) {
            throw new RuntimeException("Store exception");
        }
    }
}
