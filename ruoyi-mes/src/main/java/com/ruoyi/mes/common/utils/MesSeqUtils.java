package com.ruoyi.mes.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * MES 业务单号生成工具
 *
 * @author ruoyi
 */
public class MesSeqUtils
{
    private static final String PATTERN = "yyyyMMddHHmmss";

    private MesSeqUtils()
    {
    }

    /**
     * 生成业务单号：前缀 + 时间戳 + 4 位随机数
     *
     * @param prefix 单号前缀
     * @return 业务单号
     */
    public static String generate(String prefix)
    {
        String time = new SimpleDateFormat(PATTERN).format(new Date());
        int random = ThreadLocalRandom.current().nextInt(1000, 10000);
        return prefix + time + random;
    }
}
