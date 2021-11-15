package cn.exrick.xboot.your.jobs;

import cn.exrick.xboot.your.service.OrderService;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 统筹单过期任务
 * @author Exrickx
 */
@Slf4j
@Component
public class OrderValidJob implements Job {
    @Autowired
    private OrderService orderService;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        orderService.validOrder();
    }

    @Scheduled(cron = "0 0/5 * 1/1 * ?")
    public void executeValid() throws JobExecutionException {
        orderService.validOrder();
    }
}
