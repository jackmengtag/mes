package com.ruoyi.mes.integration.support;

import java.util.Date;

/**
 * 重试策略（架构文档 8.2 重试与死信）
 *
 * <p>建议重试间隔：10 秒、30 秒、2 分钟、10 分钟、30 分钟，超过上限后进入死信。</p>
 *
 * @author ruoyi
 */
public class RetryPolicy
{
    /** 重试间隔（秒） */
    private static final int[] DELAY_SECONDS = { 10, 30, 120, 600, 1800 };

    private RetryPolicy()
    {
    }

    /**
     * 是否应进入死信
     */
    public static boolean shouldDeadLetter(int retryCount)
    {
        return retryCount >= DELAY_SECONDS.length;
    }

    /**
     * 计算下次重试时间
     */
    public static Date nextRetryTime(int retryCount)
    {
        int idx = Math.min(retryCount, DELAY_SECONDS.length - 1);
        if (idx < 0)
        {
            idx = 0;
        }
        return new Date(System.currentTimeMillis() + DELAY_SECONDS[idx] * 1000L);
    }

    /**
     * 最大重试次数
     */
    public static int maxRetry()
    {
        return DELAY_SECONDS.length;
    }
}
