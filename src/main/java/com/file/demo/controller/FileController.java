package com.file.demo.controller;

import com.file.demo.entity.MyFile;
import com.file.demo.entity.ResponseFile;
import com.file.demo.entity.UploadedFile;
import com.file.demo.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@RestController
@RequestMapping("/api/v1")
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping(value = "/file")
    public String file() {
        return "file";
    }

    /**
     * upload file
     * @param uploadFile the file will be uploaded
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@ModelAttribute UploadedFile uploadFile,
                                        HttpServletRequest request) throws IOException {
        try {
            String url=fileService.uploadFile(uploadFile, request);
            return ResponseEntity.status(HttpStatus.OK).body(url);
        }catch(Exception e)
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Could not upload this file!");
        }
    }

    /**
     * search a file by its name
     * @param fileName of the file
     * @return a ResponseFile object with a message shows if a file is found.
     * if the file is found, the file name, file type and download url will be returned.
     */
    @GetMapping("/searchFile")
    public ResponseEntity<ResponseFile> searchFileByName(@RequestParam(value = "fileName") String fileName)
    {
        MyFile file=fileService.getFile(fileName);
        ResponseFile responseFile=null;
        //check if a file has been found
        if(file==null || file.getFileName().isEmpty())
        {
            responseFile=new ResponseFile();
            responseFile.setMessage("No file matches");
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseFile);
        }
        //a file has been found
        else
        {
            //get the downloading url
            String downloadURL= ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/static/uploadfile")
                    .path(file.getFileName())
                    .toUriString();
            responseFile=new ResponseFile(file.getFileName(),downloadURL,file.getFileType(),"File matches");
            return ResponseEntity.status(HttpStatus.OK).body(responseFile);
        }
    }
}
