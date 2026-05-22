package com.EcommMicro.order.order;

import java.math.BigDecimal;

public record OrderResponse(
        Integer id,
        String customerId,
        PaymentMethod paymentMethod,
        String reference,
        BigDecimal amount
) {
}
