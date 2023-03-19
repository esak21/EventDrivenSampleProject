package com.learning.backend.orderService.controller;

import com.learning.backend.basedomains.DTO.OrderEvent;
import com.learning.backend.basedomains.DTO.Orders;
import com.learning.backend.orderService.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    public OrderProducer orderprod;

    @PostMapping("/order")
    public String createOrder(@RequestBody Orders orders) {

        orders.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderevent = new OrderEvent();
        orderevent.setStatus("PENDING");
        orderevent.setMessage("Order status is in Pending Status..");
        orderevent.setOrders(orders);
        System.out.println(orders);
        System.out.println(orderevent);
        orderprod.sendMessages(orderevent);

        return "Order Event Has been Sent Sucessfully";

    }
}
