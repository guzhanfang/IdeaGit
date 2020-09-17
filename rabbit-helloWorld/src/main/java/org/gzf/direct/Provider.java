package org.gzf.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.gzf.util.RabbitmqUtil;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitmqUtil.getConnection();
        Channel channel = connection.createChannel();
//       创建交换机,第一个参数是交换机的名字；第二个参数是交换机的类型
        channel.exchangeDeclare("log", BuiltinExchangeType.DIRECT);
        String key = "info";
//        参数一：交换机名字；参数二：路由key；参数三：额外参数配置；参数四：传输的数据
        channel.basicPublish("log", key, null, key.getBytes());
        RabbitmqUtil.closeConnAndChannel(channel,connection);

    }
}
