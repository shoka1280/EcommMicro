package com.EcommMicro.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name="customer-service",
        url="${application.config.customer-url}"
)
@Component
public interface CustomerClient {
    @GetMapping("/{customer-Id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-Id") String id);


}
