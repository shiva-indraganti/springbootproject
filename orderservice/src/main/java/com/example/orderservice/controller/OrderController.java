package com.example.orderservice.controller;

import com.example.orderservice.clients.CustomerClient;
import com.example.orderservice.clients.FoodItemClient;
import com.example.orderservice.entity.Order;
import com.example.orderservice.model.Customer;
import com.example.orderservice.model.FoodItem;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
    private CustomerClient customerClient;

    @Autowired
    private FoodItemClient foodItemClient;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    @GetMapping("/customer/{order-id}")
    public Customer getCustomerByOrderId(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        
        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        return customerClient.getCustomerById(order.getCustomerId());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }
    
    @GetMapping("/food-item/{order-id}")
    public FoodItem getFoodItemByOrderId(@PathVariable Long orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        // Fetch food item details using Feign client
        return foodItemClient.getFoodItemById(order.getFoodItemId());
    }
        

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

