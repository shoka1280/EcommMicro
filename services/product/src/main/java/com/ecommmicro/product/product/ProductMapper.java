package com.ecommmicro.product.product;

import com.ecommmicro.product.category.Category;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toProduct(@Valid ProductRequest request) {
        if(request ==null){return  null;}
        return Product.builder().
                id(request.id()).
                prodname(request.prodname()).
                availableQuantity(request.availableQuantity()).
                description(request.description()).
                price(request.price()).
                category(
                        Category.builder()
                                .id(request.categoryId())
                                .build()).
                build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getProdname(),
                product.getAvailableQuantity(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getCatename(),
                product.getCategory().getDescription()

//
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product,double quantity) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getProdname(),
                product.getDescription(),
                product.getPrice(),
                quantity
        );
    }
}
