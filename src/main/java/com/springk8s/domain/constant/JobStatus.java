package com.springk8s.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JobStatus {

    INIT("init"),
    SUBMITTED("submitted"),
    PENDING("pending"),
    RUNNING("running"),
    FINISHED("finished"),
    KILLED("killed"),
    ERROR("error"),
    DEAD("dead"),
    ;

    private final String jobStatus;

}
