package org.gzf.fanout;

import com.rabbitmq.client.*;
import org.gzf.util.RabbitmqUtil;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();
//        创建临时队列
        String queue = channel.queueDeclare().getQueue();
//        队列与交换机进行绑定，第一个参数是队列名；第二个参数是交换机的名字,第三个参数是routingKey
        channel.queueBind(queue,"log" ,"");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("consumer-1:接收的消息是："+new String(body));
            }
        });
    }
}
