package com.relic.entity;

import lombok.*;
import org.springframework.batch.core.ExitStatus;

/**
 * @author Relic
 * @desc
 * @date 2019-11-20 10:01
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobResult {

    private String jobName;
    private Long jobId;
    private ExitStatus jobExitStatus;
    private Long timestamp;

}
