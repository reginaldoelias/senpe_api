package br.mil.mar.saudenaval.senpe.controller;

import br.mil.mar.saudenaval.senpe.services.NotificationProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationProducer notificationProducer;

    public NotificationController(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

  /*  @PostMapping("/send")
    public String sendNotification(@PathVariable String username, @RequestParam String message){
        notificationProducer.sendNotification(username, message);
        return "Notification sent to" + username;
    }*/
}
