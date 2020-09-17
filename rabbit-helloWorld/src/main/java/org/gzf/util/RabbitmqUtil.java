package org.gzf.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitmqUtil {

    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    /**
     * 配置连接端口，ip,用户名和密码
     */
    static {
        //绑定RabbitMQ主机地址
        connectionFactory.setHost("118.25.43.126");
        //绑定端口
        connectionFactory.setPort(5672);
        //输入用户名密码
        connectionFactory.setUsername("test");
        connectionFactory.setPassword("test");
        connectionFactory.setVirtualHost("/test");
    }

    /**
     * 获取连接对象
     * @return
     */
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭资源
     * @param channel
     * @param connection
     */
    public static void closeConnAndChannel(Channel channel, Connection connection){
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

