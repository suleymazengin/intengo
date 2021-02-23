package com.intengo.intengo.service.impl;

import com.intengo.intengo.domain.FileEntity;
import com.intengo.intengo.exception.RequestException;
import com.intengo.intengo.message.ApiResponse;
import com.intengo.intengo.repository.FileRepository;
import com.intengo.intengo.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Slf4j
@Service
public class FileServiceImp implements FileService {

    private final FileRepository fileRepository;


    public FileServiceImp(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    @Override
    public void saveFile(MultipartFile file) {

        try {


            fileRepository.findByFileName(file.getOriginalFilename()).ifPresent(fileEntity -> {


                throw new RequestException("There is a file with the same name");


            });


            Path copyLocation = Paths
                    .get("C:\\" + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

            String path = copyLocation.toString();
            FileEntity fileEntity = new FileEntity();
            fileEntity.setData(file.getBytes());
            fileEntity.setSize(file.getSize());
            fileEntity.setFileName(file.getOriginalFilename());
            fileEntity.setType(file.getContentType());
            fileEntity.setPath(path);
            fileRepository.save(fileEntity);

        } catch (Exception e) {

            log.info(e.getMessage());

            throw new RequestException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }


    }

    @Override
    public ApiResponse getFileByName(String name) {
        Optional<FileEntity> file = fileRepository.findByFileName(name);


        return file.map(fileEntity -> new ApiResponse(HttpStatus.OK, "Success", fileEntity))
                .orElseGet(() -> new ApiResponse(HttpStatus.BAD_REQUEST, "Data not found"));


    }

    @Override
    public ApiResponse getContentByName(String name) {
        Optional<FileEntity> file = fileRepository.findByFileName(name);


        return file.map(fileEntity -> new ApiResponse(HttpStatus.OK, "Success", fileEntity.getData()))
                .orElseGet(() -> new ApiResponse(HttpStatus.BAD_REQUEST, "Data not found"));
    }

}