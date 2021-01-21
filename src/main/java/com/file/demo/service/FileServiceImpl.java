package com.file.demo.service;

import com.file.demo.entity.MyFile;
import com.file.demo.entity.UploadedFile;
import com.file.demo.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    @Autowired
    private FileRepository fileRepository;

    // under project root
    public final static String UPLOAD_PATH_PREFIX = "static/uploadFile/";

    @Override
    public String uploadFile(UploadedFile uploadedFile,HttpServletRequest request) throws IOException {
         MultipartFile file=uploadedFile.getFile();
         Integer userId=uploadedFile.getUserId();
         //get the byte here because getBytes() cannot be used after the transferTo()
        byte[] data=file.getBytes();

         //check if the file is empty
        if(file.isEmpty()){
            return "Please select a file first";
        }
        //get the original file name
        String fileName = file.getOriginalFilename();
        //create the file path where the file will be saved
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        //create a folder to store the file
        File f = new File(realPath);
        if(!f.isDirectory()){
            f.mkdirs();
        }
         try {
            //create the real path
            File newFile = new File(f.getAbsolutePath() + File.separator + fileName);
             /*store the uploaded file to the pointed place, if the file name is the same, the new file
             will overwrite the previous one*/
            file.transferTo(newFile);
            String filePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/"  + fileName;
             //get the current time
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             Date now=new Date();
             String currentTime=sdf.format(now);

             //add this file to the database
             MyFile myFile=new MyFile(null,userId,fileName,file.getContentType(),
                     f.toString(),currentTime,data);
            fileRepository.save(myFile);

            //return the download url to the client side
            return filePath;
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
        return "Upload failed!";
    }

    /**
     * search file by its name
     * @param name
     * @return
     */
    @Override
    public List<MyFile> getFile(String name) {
        //create search parameters
        Specification<MyFile> search=new Specification<MyFile>() {
            @Override
            public Predicate toPredicate(Root<MyFile> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                //get the search attribute
                Path<Object> fileName = root.get("fileName");
                //create the search condition
                Predicate p=cb.like(fileName.as(String.class),"%"+name+"%");
                return p;
            }
        };
        List<MyFile> files=fileRepository.findAll(search);
      return  files;
    }

}

