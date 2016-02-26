package org.zgf.learn.learn.jms.activemq.p2s;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
/**
 * JMS 文本消息监听器
 *
 */
public class JMSSubListener implements MessageListener{
	
	//订阅者名称
	private final String subName ;
	
	public JMSSubListener(String subName) {
		this.subName = subName;
	}

	@Override
	public void onMessage(Message message) {
		//1. 强制转换消息
		TextMessage textMessage = (TextMessage) message;
		
		//2. 获取接收到的消息内容
		try {
			String msgContent = textMessage.getText();
			System.out.println("【" + this.subName + "】接受到的消息内容为:" + msgContent);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
