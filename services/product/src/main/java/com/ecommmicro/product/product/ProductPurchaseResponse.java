package com.ecommmicro.product.product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer id,
        String prodname,
        String discription,
        BigDecimal price,
        double quantity
) {
}
