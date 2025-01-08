package com.example.orderservice.service;

import com.example.orderservice.clients.CustomerClient;
import com.example.orderservice.clients.FoodItemClient;
import com.example.orderservice.entity.Order;
import com.example.orderservice.model.Customer;
import com.example.orderservice.model.FoodItem;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private FoodItemClient foodItemClient;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        // Fetch Customer Details
        Customer customer = customerClient.getCustomerById(order.getCustomerId());

        // Fetch Food Item Details
        FoodItem foodItem = foodItemClient.getFoodItemById(order.getFoodItemId());

        // Set Customer and Food Item Details in the Order
        order.setCustomerId(customer.getName());
        order.setFoodItemId(foodItem.getName());

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

	public Order createOrder1(Order order) {
		// TODO Auto-generated method stub
		return null;
	}
}

