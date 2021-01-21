package com.file.demo;

import com.file.demo.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
@Autowired
private FileRepository fileRepository;

    @Test
    void contextLoads() {

    }

}
