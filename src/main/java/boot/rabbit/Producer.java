package boot.rabbit;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        String  a = "asd";
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("127.0.0.1");
        f.setUsername("guest");
        f.setPassword("guest");
        f.setPort(5672);
        Connection c1 = f.newConnection();
        Channel channel1 = c1.createChannel();
        Connection c2 = f.newConnection();
        Channel channel2 = c2.createChannel();
        Connection c3 = f.newConnection();
        Channel channel3 = c3.createChannel();
        Connection c4 = f.newConnection();
        Channel channel4 = c4.createChannel();

        channel1.exchangeDeclare("xxx_exchange_direct", BuiltinExchangeType.DIRECT,true,false,null);
        channel1.queueDeclare("xxx_queue_direct",true,false,false,null);
        channel1.queueBind("xxx_queue_direct","xxx_exchange_direct","xxx_queue_direct");
        channel1.basicPublish("xxx_exchange_direct","xxx_queue_direct",null,"direct1".getBytes());
        channel1.close();
        c1.close();

        channel2.exchangeDeclare("xxx_exchange_header", BuiltinExchangeType.HEADERS,true,false,null);
        channel2.queueDeclare("xxx_queue_header",true,false,false,null);
        channel2.queueBind("xxx_queue_header","xxx_exchange_header","xxx_queue_header");
        channel2.basicPublish("xxx_exchange_header","xxx_queue_header",null,"header".getBytes());
        channel2.close();
        c2.close();

        channel3.exchangeDeclare("xxx_exchange_fanout", BuiltinExchangeType.FANOUT,true,false,null);
        channel3.queueDeclare("xxx_queue_fanout",true,false,false,null);
        channel3.queueBind("xxx_queue_fanout","xxx_exchange_fanout","xxx_queue_fanout");
        channel3.basicPublish("xxx_exchange_fanout","xxx_queue_fanout",null,"fanout".getBytes());
        channel3.close();
        c3.close();

        channel4.exchangeDeclare("xxx_exchange_topic", BuiltinExchangeType.TOPIC,true,false,null);
        channel4.queueDeclare("xxx_queue_topic",true,false,false,null);
        channel4.queueBind("xxx_queue_topic","xxx_exchange_topic","xxx_queue_topic");
        channel4.basicPublish("xxx_exchange_topic","xxx_queue_topic",null,"topic".getBytes());
        channel4.close();
        c4.close();
        System.out.println("发送完毕！");




    }
}
