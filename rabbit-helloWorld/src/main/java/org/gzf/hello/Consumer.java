package org.gzf.hello;

import com.rabbitmq.client.*;
import org.gzf.util.RabbitmqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {

        // 通过工具类获取连接对象
        Connection connection = RabbitmqUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        //  '参数1':用来声明通道对应的队列 hello
        //  '参数2':用来指定是否持久化队列 true
        //  '参数3':用来指定是否独占队列 false 一般都是不独站队列 让多个连接可以共同向一个队列生产消费消息
        //  '参数4':用来指定是否自动删除队列 false
        //  '参数5':对队列的额外配置 是一个Map类型
        channel.queueDeclare("hello",true,false,true,null);
        //参数一代表队列名，参数二是否开启自动确认机制，参数三，消费时的回调接口
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("接收的消息是："+new String(body));
            }
        });
    }

}

