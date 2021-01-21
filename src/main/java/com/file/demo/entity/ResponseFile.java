package com.file.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ResponseFile {
    private String fileName;
    private String downloadURL;
    private String fileType;
    private String message;
}
