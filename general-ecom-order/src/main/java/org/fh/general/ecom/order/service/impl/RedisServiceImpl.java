package org.fh.general.ecom.order.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.fh.general.ecom.order.model.demo.RedisModel;
import org.fh.general.ecom.order.service.IRedisService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RedisServiceImpl extends IRedisService<RedisModel> {
    private static final String REDIS_KEY = "TEST_REDIS_KEY";

    @Override
    protected String getRedisKey() {
        return REDIS_KEY;
    }
}
