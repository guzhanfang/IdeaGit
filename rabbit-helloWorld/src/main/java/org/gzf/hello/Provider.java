package org.gzf.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.gzf.util.RabbitmqUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {

    public static void main(String[] args) throws IOException, TimeoutException {

        // 获取连接对象
        Connection connection = RabbitmqUtil.getConnection();
        // 创建通道
        Channel channel = connection.createChannel();

        //queueDeclare第一个参数是队列名称，
        //第二个是是否持久化，如果是true，队列持久化，但是队列内容的持久化需要在basicProperties设置
        //第三个是是否独占队列一般是false不独占，当前Connection连接独占队列
        //第四个是消费完成后是否自动删除 true代表删除，false代表不删除
        //第五个参数是额外附加参数

        channel.queueDeclare("hello",true,false,true,null);
        //basicPublish 第一个参数代表交换机名称，第二是队列名称，第三个是额外的队列配置 第四个参数就是发送的消息
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello,world".getBytes());

        //关闭管道和连接
        RabbitmqUtil.closeConnAndChannel(channel,connection);

    }

}

