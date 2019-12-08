package com.relic.service.impl;

import com.relic.entity.TaskEntity;
import com.relic.mapper.TaskMapper;
import com.relic.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Relic
 * @desc
 * @date 2019-11-18 15:12
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Override
    public int insert(TaskEntity taskEntity) {
        return taskMapper.insert(taskEntity);
    }

    @Override
    public List<TaskEntity> getAll() {
        return taskMapper.getAll();
    }

}
