package com.file.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * entity class
 */
@Entity
@Table(name = "my_file")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyFile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    @Column(name = "file_id")
    private String fileId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "upload_dir")
    private String uploadDir;
    @Column(name = "upload_time")
    private String uploadTime;
    @Lob
    private byte[] data;
}
