package com.EcommMicro.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PurchaseRequest(
        @NotNull(message = "Product id is required")
        Integer productId,
        @Positive(message = "Quantity must be positive")
        double quantity,
        BigDecimal price
) {
}
