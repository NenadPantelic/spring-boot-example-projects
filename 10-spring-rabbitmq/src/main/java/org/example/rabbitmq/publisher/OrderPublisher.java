package org.example.rabbitmq.publisher;

import org.example.rabbitmq.config.MessagingConfig;
import org.example.rabbitmq.dto.Order;
import org.example.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        // restaurant service
        // payment service

        OrderStatus orderStatus = new OrderStatus(
                order, "PROCESSING", String.format("Order successfully placed in %s", restaurantName)
        );

        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, orderStatus);
        return "Success!!";
    }
}
