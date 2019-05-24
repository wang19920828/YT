package org.fh.general.ecom.order.model.demo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RedisModel implements Serializable {

    private String redisKey;//redis中的key

    private String name;//姓名

    private String tel;//电话

    private String address;//住址
}
