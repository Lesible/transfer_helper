package com.relic.batch.listener;

import com.relic.utils.DateFormatUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Relic
 */
@Slf4j
@Component
public class CommonJobListener extends JobExecutionListenerSupport {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job [" + jobExecution.getJobId() + "] start " + DateFormatUtils.format(jobExecution.getStartTime(), DATE_TIME_FORMAT));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Job [" + jobExecution.getJobId() + "] finish " + DateFormatUtils.format(jobExecution.getEndTime(), DATE_TIME_FORMAT));
        Date start = jobExecution.getJobParameters().getDate("timestamp");
        log.info("Job [" + jobExecution.getJobId() + "] spend {} ms", jobExecution.getEndTime().getTime() - start.getTime());
        log.info("Job [" + jobExecution.getJobId() + "] status " + jobExecution.getStatus().name());
    }
}
