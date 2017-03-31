package com.duotin.receivebroadcast;

import com.duotin.util.JsonUtils;
import com.duotin.util.MessageUtils;
import com.duotin.util.StringUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

/**
 * Created by qiguo on 16/12/7.
 */
public abstract class AbstractEvent implements IMessageCallBack {


    private static final Logger log = LoggerFactory.getLogger(AbstractEvent.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    private SimpleMessageListenerContainer msgListenerContainer;

    @Autowired
    SimpleMessageConverter simpleMessageConverter;

    @Override
    public void onMessage(Message message) {
        try {
            String json = MessageUtils.getContentAsString(message.getBody(), message.getMessageProperties());
            if (StringUtils.isNotEmpty(json)) {
                Map<String, Object> map = JsonUtils.toObject(Map.class, json);
                if (map != null) {
                    consume(map);
                } else {
                    log.error("************ json can not change to map !");
                }
            } else {
                log.error("************ message is null!");
            }
        } catch (Throwable e) {
            log.error("json pars error,json body:" + message.getBody(), e);
        }
    }



    @PostConstruct
    public void initMsgListenerAdapter() {
        Channel channel = connectionFactory.createConnection().createChannel(false);
        try {
            AMQP.Queue.DeclareOk queueDeclareOk = channel.queueDeclare();
            channel.queueBind(queueDeclareOk.getQueue(),getName(),"");
            MessageListener listener = new MessageListenerAdapter(this, simpleMessageConverter);
            msgListenerContainer = new SimpleMessageListenerContainer();
            msgListenerContainer.setConnectionFactory(connectionFactory);
            msgListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
            msgListenerContainer.setMessageListener(listener);
            msgListenerContainer.setPrefetchCount(1);
            msgListenerContainer.setConcurrentConsumers(1);
            msgListenerContainer.setQueueNames(queueDeclareOk.getQueue());
            msgListenerContainer.setAutoStartup(true);
            msgListenerContainer.setExclusive(true);
            msgListenerContainer.start();
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
    }

    protected abstract void consume(Map<String, Object> map);

}
