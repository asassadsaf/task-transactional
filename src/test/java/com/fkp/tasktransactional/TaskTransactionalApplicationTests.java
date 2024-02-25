package com.fkp.tasktransactional;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fkp.tasktransactional.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class TaskTransactionalApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        System.out.println(userMapper.selectList(Wrappers.emptyWrapper()));
    }

    @Test
    void testWriteFile() throws IOException {
        String baseDir = System.getProperty("user.dir");
        FileOutputStream fileOutputStream = new FileOutputStream(baseDir + "/files/test.txt");
        String data = "edb3c497-5642-41f2-afae-ad957a80ba02";
        fileOutputStream.write(data.getBytes(StandardCharsets.UTF_8));
        fileOutputStream.close();
    }

    @Test
    void test1(){
        System.out.println();
    }
}
