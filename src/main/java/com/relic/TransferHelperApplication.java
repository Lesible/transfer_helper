package com.relic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Relic
 * @desc 启动类
 * @date 2019-11-18 11:38
 */
@EnableBatchProcessing
@MapperScan("com.relic.mapper")
@SpringBootApplication
public class TransferHelperApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransferHelperApplication.class, args);
    }
}
