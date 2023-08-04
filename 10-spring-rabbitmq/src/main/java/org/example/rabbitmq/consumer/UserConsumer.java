package org.example.rabbitmq.consumer;

import org.example.rabbitmq.config.MessagingConfig;
import org.example.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus){
        System.out.println("Message received from the queue" + orderStatus);
    }
}
