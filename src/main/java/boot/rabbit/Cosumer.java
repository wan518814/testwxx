package boot.rabbit;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Cosumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("127.0.0.1");
        f.setUsername("guest");
        f.setPassword("guest");
        f.setPort(5672);
        Connection c = f.newConnection();
        Channel channel = c.createChannel();
        DeliverCallback deliverCallback = (cosumerTage,message)->{
            System.out.println("接受到消息"+new String(message.getBody()));

        };
        CancelCallback cancelCallback = cosumerTage ->{
            System.out.println("中断了");

        };
        channel.basicConsume("xxx_queue_direct",true,deliverCallback,cancelCallback);
        channel.basicConsume("xxx_queue_fanout",true,deliverCallback,cancelCallback);
        channel.basicConsume("xxx_queue_header",true,deliverCallback,cancelCallback);
        channel.basicConsume("xxx_queue_topic",true,deliverCallback,cancelCallback);
        channel.close();
        c.close();

    }
}
