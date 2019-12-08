package com.relic.batch.step;

import com.relic.entity.Person;
import com.relic.entity.TaskEntity;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Relic
 * @desc
 * @date 2019-11-20 10:31
 */
@Component
public class StepDefinition {

    @Resource
    private StepBuilderFactory stepBuilderFactory;

    @Resource
    private ItemReader<Person> personCursorReader;

    @Resource
    private ItemWriter<TaskEntity> compositeWriter;

    @Resource
    private ItemProcessor<Person,TaskEntity> personTaskProcessor;

    @Bean
    public Step batchStep() {
        return stepBuilderFactory.get("batchStep").<Person,TaskEntity>chunk(50).reader(personCursorReader)
                .processor(personTaskProcessor).writer(compositeWriter).build();
    }


}
