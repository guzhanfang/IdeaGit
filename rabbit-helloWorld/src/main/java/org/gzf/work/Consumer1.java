package org.gzf.work;

import com.rabbitmq.client.*;
import org.gzf.util.RabbitmqUtil;

import java.io.IOException;

public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtil.getConnection();
        final Channel channel = connection.createChannel();
//        管道一次只能传递一个数据
        channel.basicQos(1);
        channel.queueDeclare("work",true,false,false,null);
//        第二个参数，false关闭消息自动确认机制
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接收的消息是-1："+new String(body));
//                消息消费完成后，手动确认消息已经被消费
//                第一个参数为long类型，为消息在管道中的位置；
//                第二个参数true确认消息已经被消费
                System.out.println(envelope.getDeliveryTag());
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        });
    }
}
