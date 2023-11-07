package com.ifindpal.v1.scheduler;

import com.ifindpal.v1.entity.User;
import com.ifindpal.v1.mapper.UserMapper;
import com.ifindpal.v1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Ansel Zhong
 * @description:
 * @date 2023/11/7 6:20
 * @ProjectName ifindpal-v1.0
 **/
@Component
public class Scheduler {

    @Autowired
    private IUserService userService;
    @Scheduled(cron = "0 0 0 * * ?")
    public void taskPostCounts(){
        List<User> list = userService.list();
        for (User user : list) {
            user.setPostCountPerDay(0);
        }
        userService.updateBatchById(list);
    }
}
