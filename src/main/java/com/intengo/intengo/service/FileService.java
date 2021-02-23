package com.intengo.intengo.service;

import com.intengo.intengo.domain.FileEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface FileService {

    FileEntity saveFile(MultipartFile file) throws IOException;
}
