package com.example.orderservice.clients;

import com.example.orderservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customerservice")
public interface CustomerClient {
    @GetMapping("/api/customers/{id}")
    Customer getCustomerById(@PathVariable String string);
}
