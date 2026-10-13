package com.ruoyi.mes.integration.support;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 外部报文 Map 取值工具
 *
 * @author ruoyi
 */
public class MapUtils
{
    private MapUtils()
    {
    }

    public static String str(Map<String, Object> map, String key)
    {
        Object v = map == null ? null : map.get(key);
        return v == null ? null : String.valueOf(v);
    }

    public static String str(Map<String, Object> map, String key, String defaultValue)
    {
        String v = str(map, key);
        return v == null || v.isEmpty() ? defaultValue : v;
    }

    public static Long lng(Map<String, Object> map, String key)
    {
        String v = str(map, key);
        if (v == null || v.isEmpty())
        {
            return null;
        }
        try
        {
            return Long.valueOf(v);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }

    public static Integer integer(Map<String, Object> map, String key)
    {
        String v = str(map, key);
        if (v == null || v.isEmpty())
        {
            return null;
        }
        try
        {
            return Integer.valueOf(v);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }

    public static BigDecimal decimal(Map<String, Object> map, String key)
    {
        String v = str(map, key);
        if (v == null || v.isEmpty())
        {
            return null;
        }
        try
        {
            return new BigDecimal(v);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }
}
