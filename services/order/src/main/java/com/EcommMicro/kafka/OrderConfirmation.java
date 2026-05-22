package com.EcommMicro.kafka;

import com.EcommMicro.customer.CustomerResponse;
import com.EcommMicro.order.order.PaymentMethod;
import com.EcommMicro.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products) {

}
