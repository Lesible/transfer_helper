package com.relic.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Relic
 * @desc
 * @date 2019-11-18 14:56
 */
@Data
public class TaskEntity {

    private Long id;

    private String taskName;

    private Integer taskStatus;

    private String taskDescription;

    private Date updateDate;


}
