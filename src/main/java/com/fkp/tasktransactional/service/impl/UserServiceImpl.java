package com.fkp.tasktransactional.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fkp.tasktransactional.entity.User;
import com.fkp.tasktransactional.mapper.UserMapper;
import com.fkp.tasktransactional.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private int count = 0;

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void taskTest() {
        count ++;
        System.out.println("task exec: " + count);
        String uuid = UUID.randomUUID().toString();
        User user = User.builder().id(uuid).name("fkp").age(25).build();
        int insert = userMapper.insert(user);
        if(insert != 1){
            System.out.println("insert error. user: {}" + user);
        }
        List<User> users = userMapper.selectList(Wrappers.emptyWrapper());
        String baseDir = System.getProperty("user.dir");
        try (FileOutputStream fileOutputStream = new FileOutputStream(baseDir + "/files/test.txt")){
            fileOutputStream.write(String.valueOf(count).getBytes(StandardCharsets.UTF_8));
            fileOutputStream.write("\n".getBytes(StandardCharsets.UTF_8));
            for (User data : users) {
                fileOutputStream.write(data.getId().getBytes(StandardCharsets.UTF_8));
            }
            fileOutputStream.write("\n".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
