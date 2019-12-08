package com.relic.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Relic
 * @desc
 * @date 2019-11-19 19:52
 */
@Data
public class Person {

    private Long id;
    private String name;
    private Integer age;
    private Integer gender;
    private Date updateDate;
}
