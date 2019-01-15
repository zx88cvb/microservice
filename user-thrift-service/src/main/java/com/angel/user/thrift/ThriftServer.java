package com.angel.user.thrift;

import com.angel.thrift.user.UserService;
import org.apache.thrift.TProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author JingXiang Bi
 * @date 2019/1/15
 */
@Configuration
public class ThriftServer {

    @Value("${server.port}")
    private int servicePort;

    @Resource
    private UserService.Iface usreService;

    @PostConstruct
    public void startThriftServer() {
        TProcessor tProcessor = new UserService.Processor<>(usreService);
    }
}
