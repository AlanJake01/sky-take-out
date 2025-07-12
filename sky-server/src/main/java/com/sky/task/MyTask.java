package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * ClassName:MyTask
 * Description:
 *
 * @Author:AlanJake
 * @Create:2025/7/11下午9:16
 * @Version:
 */

@Component
@Slf4j
public class MyTask {
    @Scheduled(cron = "0/5 * * * * ?")
    public void executeTask(){
        log.info("定时任务执行：{}",LocalDateTime.now());
    }
}
