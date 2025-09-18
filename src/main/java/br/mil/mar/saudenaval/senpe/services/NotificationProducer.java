package br.mil.mar.saudenaval.senpe.services;

import br.mil.mar.saudenaval.senpe.entities.User;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationProducer {

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendNotification(User user, String title, String message) {
        Map<String, String> json = new HashMap<>();
        json.put("title", title);

        json.put("message", message);

        JSONObject jsonObject = new JSONObject(json);
        String jsonString = jsonObject.toString();

        String routingKey = "notification." + user.getUsername();
        rabbitTemplate.convertAndSend("notification-exchange", routingKey, jsonString);
    }
}
