package com.angel.user.thrift;

import com.angel.thrift.user.UserService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 注册服务
 * @author JingXiang Bi
 * @date 2019/1/16
 */
@Component
public class ServiceProvider {

    @Value("${thrift.user.ip}")
    private String serverIp;

    @Value("${thrift.user.port}")
    private int serverPort;

    /**
     * 创建服务
     * @return 返回服务 client对象
     */
    public UserService.Client getUserService() {
        TSocket socket = new TSocket(serverIp, serverPort, 3000);

        TTransport tTransport = new TFastFramedTransport(socket);
        try {
            tTransport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(tTransport);

        UserService.Client client = new UserService.Client(protocol);

        return client;
    }
}
