package com.EcommMicro.order.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid  OrderRequest OrderRequest) {
        Integer orderId = orderService.createOrder(OrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> finalAll()
    {
        return ResponseEntity.ok(orderService.getAllOrders());
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> finById(Integer id)
    {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }


}
