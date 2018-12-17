package com.cyb.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author cyb
 * @date 2018/12/16 - 17:22
 */
@Service
public class ScheduledService {

    /**
     * second,minute,hour,day of month,month,day of week
     * 0 * * * * MON-FRI
     *
     * *代表的是任何时候
     * ,代表枚举(1,2) 就是两个值都满足条件@Scheduled(cron = "0，1,2 * * * * MON-FRI") 周一到周五0，1,2秒的时候触发
     * ? 日和星期有冲突的时候
     * -代表的是区间(MON-FRI周一到周五) @Scheduled(cron = "0-4 * * * * MON-FRI") 周一到周五0，1,2,3,4秒的时候触发
     * /代表的是间隔多久 (1/2) 从1开始每隔2 @Scheduled(cron = "0/4 * * * * MON-FRI") 周一到周五0秒开始，每隔4秒执行一次
     * 上述表达式代表的是 周一到周五整分的时候触发   注意要开启@EnableScheduling
     *
     * 【0 0/5 14,18 * * ?】 每天14点整，和18点整，每隔5分钟执行一次
     * 【0 15 10 ? * 1-6】 每个月的周一至周六10:15分执行一次
     * 【0 0 2 ? * 6L】每个月的最后一个周六凌晨2点执行一次
     * 【0 0 2 LW * ?】每个月的最后一个工作日凌晨2点执行一次
     * 【0 0 2-4 ? * 1#1】每个月的第一个周一凌晨2点到4点期间，每个整点都执行一次；
     *
     */
    @Scheduled(cron = "0 * * * * MON-FRI")
    public void hello(){
        System.out.println("hello.....");
    }
}
