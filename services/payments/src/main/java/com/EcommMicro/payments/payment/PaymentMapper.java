package com.EcommMicro.payments.payment;

import org.springframework.stereotype.Repository;

@Repository
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        return Payment.builder().
                id(request.id())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .build();
    }
}
