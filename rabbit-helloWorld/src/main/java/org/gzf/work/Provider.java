package org.gzf.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.gzf.util.RabbitmqUtil;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection =  RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",true,false,false,null);
        for (int i = 0;i<1000;i++){
            channel.basicPublish("","work",null,(i+":Work Queue").getBytes());
        }
        RabbitmqUtil.closeConnAndChannel(channel,connection);
    }
}
