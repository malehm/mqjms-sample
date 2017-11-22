package com.malehm.mqjms;

import javax.jms.ConnectionFactory;

import org.junit.rules.ExternalResource;

import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsConstants;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;

public class MqConnection extends ExternalResource {
	
	private JmsConnectionFactory factory;

	@Override
	protected void before() throws Throwable {
		JmsFactoryFactory ff = JmsFactoryFactory.getInstance(JmsConstants.WMQ_PROVIDER);
		factory = ff.createConnectionFactory();
		factory.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
		factory.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, "QM1");
		factory.setStringProperty(WMQConstants.WMQ_HOST_NAME, "localhost");
		factory.setIntProperty(WMQConstants.WMQ_PORT, 1414);
		factory.setStringProperty(WMQConstants.WMQ_CHANNEL, "DEV.ADMIN.SVRCONN");
		factory.setStringProperty(WMQConstants.WMQ_APPLICATIONNAME, "My Application");
	}
	
	public ConnectionFactory getJMSConnectionFactory() {
		return factory;
	}
}