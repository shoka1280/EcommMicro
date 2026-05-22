package com.EcommMicro.product;

import java.math.BigDecimal;

public record PurchaseResponse(
    Integer productId,
    String discription,
    BigDecimal price,
    double quantity
) {
}
