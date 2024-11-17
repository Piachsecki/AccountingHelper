package com.piasecki.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
    void uploadFile(MultipartFile file);
}
