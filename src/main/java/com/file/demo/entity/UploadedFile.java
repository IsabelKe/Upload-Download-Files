package com.file.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


/**
 *receive data as an UploadedFile object from the client side
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UploadedFile {
    private MultipartFile file;
    private Integer userId;
    private String fileType;
}
