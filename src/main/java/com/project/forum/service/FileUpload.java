package com.project.forum.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUpload {

    String uploadFile(String id, MultipartFile multipartFile) throws IOException;
}
