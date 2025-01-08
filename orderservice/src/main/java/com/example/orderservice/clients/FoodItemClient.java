package com.example.orderservice.clients;

import com.example.orderservice.model.FoodItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fooditemservice")
public interface FoodItemClient {
    @GetMapping("/api/food-items/{id}")
    FoodItem getFoodItemById(@PathVariable String string);
}
