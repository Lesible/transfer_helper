package com.relic.controller;

import com.relic.entity.JobResult;
import com.relic.entity.TaskEntity;
import com.relic.service.JobLaunchService;
import com.relic.service.TaskService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * @author Relic
 * @desc
 * @date 2019-11-18 15:10
 */
@RestController
public class TestController {

    @Resource
    private TaskService taskService;

    @Resource
    private JobLaunchService jobLaunchService;

    @Resource
    private JobOperator jobOperator;

    @Resource
    private JobExplorer jobExplorer;

    @Resource
    private Job jobWithParams;

    @RequestMapping("/insert")
    public String insert() {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(1111L);
        taskEntity.setTaskStatus(0);
        taskEntity.setTaskDescription("测试来啊");
        taskEntity.setTaskName("测试任务1");
        taskService.insert(taskEntity);
        return "插入成功";
    }

    @RequestMapping("/query")
    public List<TaskEntity> query() {
        return taskService.getAll();
    }

    @RequestMapping("/start/{gender}")
    public JobResult start(@PathVariable("gender") Integer gender) {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("timestamp", Calendar.getInstance().getTime())
                .addString("gender", gender.toString()).toJobParameters();
        return jobLaunchService.launchJob(jobWithParams, jobParameters);
    }

    @RequestMapping("/stop/{jobId}")
    public String stop(@PathVariable("jobId") Long jobId) {
        try {
            List<JobExecution> jobExecutions = getJobExecutions(jobId);
            for (JobExecution jobExecution : jobExecutions) {
                if (jobExecution.isRunning()) {
                    jobOperator.stop(jobExecution.getId());
                    return "停止成功";
                }
            }
        } catch (NoSuchJobExecutionException e) {
            return String.format("没有该任务id%d", jobId);
        } catch (JobExecutionNotRunningException e) {
            return String.format("id:%d的任务没有在运行", jobId);
        }
        return "任务已完成";
    }

    private List<JobExecution> getJobExecutions(Long jobId) {
        JobInstance jobInstance = jobExplorer.getJobInstance(jobId);
        return jobExplorer.getJobExecutions(jobInstance);
    }

    @RequestMapping("/restart/{jobId}")
    public String restart(@PathVariable("jobId") Long jobId) throws Exception {
        List<JobExecution> jobExecutions = getJobExecutions(jobId);
        for (JobExecution jobExecution : jobExecutions) {
            if (jobExecution.getStatus().equals(BatchStatus.STOPPED)) {
                jobOperator.restart(jobExecution.getId());
            }
        }
        return "重启成功";
    }
}
