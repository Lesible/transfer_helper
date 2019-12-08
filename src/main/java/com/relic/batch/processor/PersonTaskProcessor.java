package com.relic.batch.processor;

import com.relic.entity.Person;
import com.relic.entity.TaskEntity;
import com.relic.utils.SnowFlake;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Relic
 * @desc
 * @date 2019-11-20 13:37
 */
@Component
public class PersonTaskProcessor implements ItemProcessor<Person, TaskEntity> {

    @Override
    public TaskEntity process(Person person) {
        TaskEntity task = new TaskEntity();
        task.setId(SnowFlake.newId());
        task.setTaskName(person.getName());
        task.setTaskStatus(1);
        task.setTaskDescription(person.getId().toString());
        task.setUpdateDate(new Date());
        return task;
    }
}
