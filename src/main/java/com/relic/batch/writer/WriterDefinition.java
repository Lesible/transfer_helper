package com.relic.batch.writer;

import com.relic.entity.TaskEntity;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Relic
 * @desc
 * @date 2019-11-20 11:03
 */
@Component
public class WriterDefinition {

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Bean
    public MyBatisBatchItemWriter<TaskEntity> taskWriter() {
        MyBatisBatchItemWriter<TaskEntity> writer = new MyBatisBatchItemWriter<TaskEntity>();
        writer.setSqlSessionFactory(sqlSessionFactory);
        writer.setStatementId("com.relic.mapper.TaskMapper.insert");
        return writer;
    }

    @Bean
    public MyBatisBatchItemWriter<TaskEntity> personWriter() {
        MyBatisBatchItemWriter<TaskEntity> writer = new MyBatisBatchItemWriter<TaskEntity>();
        writer.setSqlSessionFactory(sqlSessionFactory);
        writer.setStatementId("com.relic.mapper.PersonMapper.update");
        return writer;
    }

    @Bean
    public CompositeItemWriter<TaskEntity> compositeWriter(ItemWriter<TaskEntity> taskWriter,ItemWriter<TaskEntity> personWriter) {
        List<ItemWriter<? super TaskEntity>> writerList = new ArrayList<>();
        writerList.add(taskWriter);
        writerList.add(personWriter);
        CompositeItemWriter<TaskEntity> writer = new CompositeItemWriter<>();
        writer.setDelegates(writerList);
        return writer;
    }

}
