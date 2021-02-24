package com.intengo.intengo.controller;

import com.intengo.intengo.message.ApiResponse;
import com.intengo.intengo.message.ResponseMessage;
import com.intengo.intengo.service.FileService;
import com.intengo.intengo.utilty.Utility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/file/{name}")
    public ApiResponse getFileByName(@PathVariable("name") String name) {

        return fileService.getFileByName(name);

    }

    @GetMapping("/file/content/{name}")
    public ApiResponse getFileContentByName(@PathVariable("name") String name) {

        return fileService.getContentByName(name);

    }

}
