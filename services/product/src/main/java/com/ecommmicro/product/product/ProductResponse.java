package com.ecommmicro.product.product;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String prodname,
        double availableQuantity,
        String description,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String categoryDescription
) {
}
