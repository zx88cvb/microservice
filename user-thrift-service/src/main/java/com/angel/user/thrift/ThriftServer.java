package com.angel.user.thrift;

import com.angel.thrift.user.UserService;
import com.angel.thrift.user.UserInfo;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService.Iface userService;

    /*@Autowired
    private UserService userService;*/

    /**
     * 服务启动仅执行一次  相当于init()
     */
    @PostConstruct
    public void startThriftServer() {
        TProcessor tProcessor = new UserService.Processor<>(userService);

        TNonblockingServerSocket socket = null;
        try {
            socket = new TNonblockingServerSocket(servicePort);
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        TNonblockingServer.Args args =  new TNonblockingServer.Args(socket);
        args.processor(tProcessor);
        args.transportFactory(new TFramedTransport.Factory());
        args.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new TNonblockingServer(args);

        server.serve();
    }

}
