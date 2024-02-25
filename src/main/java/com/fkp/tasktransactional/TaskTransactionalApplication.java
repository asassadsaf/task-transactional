package com.fkp.tasktransactional;

import cn.hutool.cron.CronUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fkp.tasktransactional.mapper.UserMapper;
import com.fkp.tasktransactional.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = "com.fkp.tasktransactional.mapper")
public class TaskTransactionalApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TaskTransactionalApplication.class, args);
        UserService userService = run.getBean(UserService.class);
        CronUtil.schedule("0/1 * * * * ? *", (Runnable) userService::taskTest);
        CronUtil.setMatchSecond(true);
        CronUtil.start();
    }

}
