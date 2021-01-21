package com.file.demo.service;

import com.file.demo.entity.MyFile;
import com.file.demo.entity.UploadedFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface FileService {
   String uploadFile(UploadedFile file,HttpServletRequest request) throws IOException;
   List<MyFile> getFile(String name);
}
