package com.relic.utils;


/**
 * @author Relic
 */
public class SnowFlake {
    /**
     * 起始的时间戳
     */
    private final static long START_TIME = 1557825652094L;
    /**
     * 每一部分占用的位数
     */
    private final static long WORKER_ID_BITS = 5L;
    private final static long DATA_CENTER_ID_BITS = 5L;
    private final static long SEQUENCE_BITS = 12L;
    /**
     * 每一部分的最大值
     */
    private final static long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    private final static long MAX_DATA_CENTER_ID = ~(-1L << DATA_CENTER_ID_BITS);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BITS);
    /**
     * 每一部分向左的位移
     */
    private final static long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private final static long DATA_CENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private final static long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;
    private static SnowFlake SNOW_FLAKE = new SnowFlake(1L, 1L);
    private long dataCenterId;
    private long workerId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    private SnowFlake(long workerId, long dataCenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("dataCenter Id can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    public static long newId() {
        return SNOW_FLAKE.nextId();
    }

    private synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0L) {
                timestamp = tilNextMillis();
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return (timestamp - START_TIME) << TIMESTAMP_SHIFT
                | dataCenterId << DATA_CENTER_ID_SHIFT
                | workerId << WORKER_ID_SHIFT
                | sequence;
    }

    private long tilNextMillis() {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}