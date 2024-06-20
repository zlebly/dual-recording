package com.georsoft.business.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class GenerateUtils {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static final AtomicLong sequence = new AtomicLong(0);

    public static synchronized String IdGenerate() {
        Date now = new Date();
        String datePart = dateFormat.format(now); // 获取当前日期时间的部分
        long seq = sequence.incrementAndGet(); // 递增序列号
        // 确保序列号部分不超过5位
        String seqPart = String.format("%03d", seq);

        // 拼接日期和序列号部分
        return datePart + seqPart;
    }
}
