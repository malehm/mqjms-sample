package com.malehm.mqjms;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import org.junit.Rule;
import org.junit.Test;

public class MqConnectionSample {

    private static final String USER = "admin";
    private static final String PASSW0RD = "passw0rd";
    private static final int ACKNOWLEGE_MODE = JMSContext.AUTO_ACKNOWLEDGE;

    @Rule
    public MqConnection mqConnection = new MqConnection();

    @Test
    public void test() throws JMSException {
        try (JMSContext jmsContext = this.mqConnection.getJMSConnectionFactory()
                .createContext(USER, PASSW0RD, ACKNOWLEGE_MODE)) {
            jmsContext.createProducer().send(jmsContext.createQueue("DEV.QUEUE.1"),
                                             jmsContext.createTextMessage("Hallo World!"));
        }
    }
}
