package com.file.demo.repository;

import com.file.demo.entity.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<MyFile,String>, JpaSpecificationExecutor<MyFile> {
    /**
     * search a file by its name
     * @param name of the file
     * @return null or the matched file
     */
    @Query("from MyFile where fileName=?1")
    public MyFile getFileByName(String name);
}
