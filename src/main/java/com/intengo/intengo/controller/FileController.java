package com.intengo.intengo.controller;

import com.intengo.intengo.message.ResponseMessage;
import com.intengo.intengo.service.FileService;
import com.intengo.intengo.utilty.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api")
@Slf4j
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> handleFileUpload(@RequestParam("file") MultipartFile file) {
        List<String> contentTypes = Utility.contentTypes;
        String message = "";
        if (!contentTypes.contains(file.getContentType())) {
            message = "Please add a file with different extension";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }

        try {
            fileService.saveFile(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

        } catch (Exception e) {

            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

        }

    }

}
