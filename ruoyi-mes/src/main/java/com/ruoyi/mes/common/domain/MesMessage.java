package com.ruoyi.mes.common.domain;

import java.io.Serializable;

/**
 * 统一消息信封（消息头 + 载荷）
 *
 * @author ruoyi
 */
public class MesMessage<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    private MesMessageHeader header;

    private T payload;

    public MesMessage()
    {
    }

    public MesMessage(MesMessageHeader header, T payload)
    {
        this.header = header;
        this.payload = payload;
    }

    public MesMessageHeader getHeader()
    {
        return header;
    }

    public void setHeader(MesMessageHeader header)
    {
        this.header = header;
    }

    public T getPayload()
    {
        return payload;
    }

    public void setPayload(T payload)
    {
        this.payload = payload;
    }
}
