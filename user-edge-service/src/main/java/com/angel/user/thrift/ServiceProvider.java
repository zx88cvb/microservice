package com.angel.user.thrift;

import com.angel.thrift.message.MessageService;
import com.angel.thrift.user.UserService;
import com.angel.user.constant.ServiceType;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.*;
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

    @Value("${thrift.message.ip}")
    private String messageServerIp;

    @Value("${thrift.message.port}")
    private int messageServerPort;

    /**
     * 创建服务
     * @return 返回服务 client对象
     */
    public UserService.Client getUserService() {
        return getService(serverIp, serverPort, ServiceType.USER);
    }

    /**
     * 创建消息验证服务
     * @return 返回服务 client对象
     */
    public MessageService.Client getMessageService() {
        return getService(messageServerIp, messageServerPort, ServiceType.MESSAGE);
    }

    public <T> T getService(String ip, int port, ServiceType serviceType) {
        TSocket socket = new TSocket(serverIp, serverPort, 3000);

        TTransport tTransport = new TFramedTransport(socket);
        try {
            tTransport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(tTransport);

        TServiceClient result = null;
        switch (serviceType) {
            case USER:
                result = new UserService.Client(protocol);
                break;
            case MESSAGE:
                result = new MessageService.Client(protocol);
                break;
        }

        return (T)result;
    }
}
