package com.relic.batch.job;

import com.relic.batch.listener.CommonJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Relic
 * @desc
 * @date 2019-11-20 10:29
 */
@Component
public class BatchJob {

    @Resource
    private JobBuilderFactory jobBuilderFactory;

    @Resource
    private Step batchStep;

    @Bean(name = "jobWithParams")
    public Job jobWithParams(CommonJobListener commonJobListener) {
        return jobBuilderFactory.get("jobWithParams")
                .incrementer(new RunIdIncrementer())
                .listener(commonJobListener)
                .flow(batchStep)
                .end()
                .build();
    }

}
