package com.relic.batch.reader;

import com.relic.entity.Person;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Relic
 * @desc
 * @date 2019-11-20 10:43
 */
@Component
public class ReaderDefinition {

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Bean
    @StepScope
    public MyBatisCursorItemReader<Person> personCursorReader(@Value("#{jobParameters[gender]}") String gender) {
        MyBatisCursorItemReader<Person> personReader = new MyBatisCursorItemReader<Person>();
        personReader.setSqlSessionFactory(sqlSessionFactory);
        personReader.setQueryId("com.relic.mapper.PersonMapper.selectByGender");
        Map<String, Object> map = new HashMap<String, Object>(4);
        map.put("gender", Integer.valueOf(gender));
        personReader.setParameterValues(map);
        return personReader;
    }

}
