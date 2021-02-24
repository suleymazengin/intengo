package com.intengo.intengo.service;

import com.intengo.intengo.message.ApiResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileService {

    void saveFile(MultipartFile file) throws IOException;

    ApiResponse getFileByName(String name);

    ApiResponse getContentByName(String name);
}
