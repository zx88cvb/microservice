package com.angel.user.utils;

import com.angel.thrift.user.UserInfo;
import com.angel.thrift.user.UserService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author JingXiang Bi
 * @date 2019/1/23
 */
public class TestUtil {
    public static void main(String[] args) {
        TSocket socket = new TSocket("127.0.0.1", 7911, 3000);

        TTransport tTransport = new TFramedTransport(socket);
        try {
            tTransport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
        TProtocol protocol = new TBinaryProtocol(tTransport);

        UserService.Client client = new UserService.Client(protocol);

        try {
            UserInfo userById = client.getUserById(1);
            System.out.println(userById);
        } catch (TException e) {
            e.printStackTrace();
        }

    }
}
