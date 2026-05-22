package com.EcommMicro.order.order;

import com.EcommMicro.product.PurchaseRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,

        String reference,
        @Positive(message="Order amount must be positive")
        BigDecimal amount,
        @NotNull(message = "Should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer id is required")
        String customerId,
        @NotEmpty(message = "Order must contain at least one product")
        List<PurchaseRequest> products) {
}
