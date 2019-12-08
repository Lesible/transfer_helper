package com.relic.mapper;

import com.relic.entity.Person;

import java.util.List;
import java.util.Map;

/**
 * @author Relic
 * @desc
 * @date 2019-11-20 10:46
 */
public interface PersonMapper {

    /**
     * 查询所有person
     *
     * @return person的集合
     */
    List<Person> selectAll();

    /**
     * 根据性别查询person
     *
     * @param params 性别
     * @return person集合
     */
    List<Person> selectByGender(Map<String, Object> params);

    /**
     * 更新person
     *
     * @param person person
     * @return 受影响条数
     */
    int update(Person person);

}
