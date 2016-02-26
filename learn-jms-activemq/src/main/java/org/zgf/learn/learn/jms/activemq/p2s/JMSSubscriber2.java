package org.zgf.learn.learn.jms.activemq.p2s;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * JMS 生产者
 */
public class JMSSubscriber2 {
	
	//设置默认的用户名、密码、连接地址
	private static final String USE = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD= ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BOOKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) throws Exception{
		//1. 创建JMS 连接工程
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USE, PASSWORD,BOOKERURL);
		//2. 创建JMS 连接
		Connection connection = connectionFactory.createConnection();
		//3. 启动JMS 连接
		connection.start();
		//4. 创建JMS 会话,不需要开启事务，提交方式为自动提交
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5. 创建JMS 消息队列
		Destination destination = session.createTopic(JMSPublisher.TOPICNAME);
		
		//6. 创建JMS 消息消费者
		MessageConsumer messageConsumer = session.createConsumer(destination);
		//7. 为消费者设置监听器
		messageConsumer.setMessageListener(new JMSSubListener("订阅者二"));
		
		//不能关闭连接，关闭之后就不能接受到消息了
	}

}
