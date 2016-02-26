package org.zgf.learn.learn.jms.activemq.p2p;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
/**
 * JMS 文本消息监听器
 *
 */
public class JMSTextMsgListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		//1. 强制转换消息
		TextMessage textMessage = (TextMessage) message;
		
		//2. 获取接收到的消息内容
		try {
			String msgContent = textMessage.getText();
			System.out.println("接受到的消息内容为:" + msgContent);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
