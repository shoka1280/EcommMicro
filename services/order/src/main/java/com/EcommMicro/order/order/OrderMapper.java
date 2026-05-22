package com.EcommMicro.order.order;

import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest) {
        return new Order().builder().
        id(orderRequest.id()).
                customerId(orderRequest.customerId()).
                paymentMethod(orderRequest.paymentMethod()).
                reference(orderRequest.reference()).
                totalAmount(orderRequest.amount()).
                build();
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getCustomerId(),
                order.getPaymentMethod(),
                order.getReference(),
                order.getTotalAmount()
        );

    }
}