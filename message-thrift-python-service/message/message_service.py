# coding: utf-8
import smtplib
from email.header import Header
from email.mime.text import MIMEText

from message.api import MessageService
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer

sender = 'zx88cvb@163.com'
authCode = 'Xiang19951101'
class MessageServiceHandler:
    def sendMobileMessage(self, mobile, message):
        """
        Parameters:
         - mobile
         - message
        """
        print("sendMobileMessage, mobile: %s, message:%s", mobile, message)
        pass

    def sendEmailMessage(self, email, message):
        """
        Parameters:
         - email
         - message
        """
        print("sendEmailMessage, email: %s , message: %s", email, message)
        messageObj = MIMEText(message, "plain", "utf-8")
        messageObj['From'] = sender
        messageObj['To'] = email
        messageObj['Subject'] = Header("测试邮件", "utf-8")
        try:
            smtpObj = smtplib.SMTP("smtp.163.com")
            smtpObj.login(sender, authCode)
            smtpObj.sendmail(sender, [email], messageObj.as_string())
            print("send mail success")
            return True
        except smtplib.SMTPException as ex:
            print(ex)
            return False


if __name__ == "__main__":
    handler = MessageServiceHandler()
    # handler.sendEmailMessage("295389754@qq.com", "哈哈啊")
    processor = MessageService.Processor(handler)
    transport = TSocket.TServerSocket('127.0.0.1', 9090)
    tfactory = TTransport.TFramedTransportFactory()
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()
    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)

    print("starting thrift server in python")
    server.serve()
    print("done!")