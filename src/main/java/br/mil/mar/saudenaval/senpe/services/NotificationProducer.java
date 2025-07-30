package br.mil.mar.saudenaval.senpe.services;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private TopicExchange exchange;

    public void createUserQueue(String username){
        String queueName = "notifications." + username;
        Queue queue = new Queue(queueName,false);
        rabbitAdmin.declareQueue(queue);

        Binding binding = BindingBuilder.bind(queue).to(exchange).with("notification."+username);
        rabbitAdmin.declareBinding(binding);
    }
}
