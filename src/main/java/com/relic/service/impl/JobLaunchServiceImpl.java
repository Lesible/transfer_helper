package com.relic.service.impl;

import com.relic.entity.JobResult;
import com.relic.service.JobLaunchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * @author Relic
 */
@Slf4j
@Service
public class JobLaunchServiceImpl implements JobLaunchService {

    @Resource
    private JobLauncher jobLauncher;

    @Override
    public JobResult launchJob(Job job, JobParameters jobParameters) {
        try {
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            return JobResult.builder().jobName(job.getName()).jobId(jobExecution.getJobId())
                    .jobExitStatus(jobExecution.getExitStatus()).timestamp(Calendar.getInstance().getTimeInMillis()).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException("launch job exception ", e);
        }
    }
}
