package com.relic.mapper;

import com.relic.entity.TaskEntity;

import java.util.List;

/**
 * @author Relic
 * @desc
 * @date 2019-11-18 14:52
 */
public interface TaskMapper {

    /**
     * 插入任务
     *
     * @param taskEntity 任务实体类
     * @return int 成功的条数
     */
    int insert(TaskEntity taskEntity);

    /**
     * 获取所有的task
     *
     * @return 所有的task任务
     */
    List<TaskEntity> getAll();
}
