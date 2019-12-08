package com.relic.service;

import com.relic.entity.JobResult;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;

/**
 * @author Relic
 * @desc
 * @date 2019-11-20 10:04
 */
public interface JobLaunchService {
    /**
     * 运行任务并返回结果
     *
     * @param job    任务
     * @param params 参数
     * @return 任务返回结果
     */
    JobResult launchJob(Job job, JobParameters params);
}
