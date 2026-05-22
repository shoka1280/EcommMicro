package com.ecommmicro.product.product;

import com.ecommmicro.product.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repo;
    private final ProductMapper mapper;
    public Integer createProduct(@Valid ProductRequest request) {
        var product = mapper.toProduct(request);

        return repo.save(product).getId();

    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> purchaseRequests) {
        //Product id
        var productIds=purchaseRequests.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        //getting list of products from db
        var storedProducts=repo.findAllByIdInOrderById(productIds);
        //checking availability from actual products availble quantity
        if(productIds.size()!=storedProducts.size()){
            throw new ProductPurchaseException("One or more products not found");
        }
        var storedRequest =purchaseRequests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();

        var purchasedProducts=new ArrayList<ProductPurchaseResponse>();

        for(int i=0;i<storedProducts.size();i++){
            //getting stored prdoucts
            var product=storedProducts.get(i);
            //getting corresponding request for products
            var productRequest=storedRequest.get(i);

            //comparing available quantity with requested quantity
            if(product.getAvailableQuantity()<productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient quantity for product id: "
                        +product.getId());
            }
            //deducting the quantity
            product.setAvailableQuantity(
                    product.getAvailableQuantity()-productRequest.quantity()
            );
            repo.save(product);
            //adding to purchased products list
            purchasedProducts.add(
                    mapper.toProductPurchaseResponse(product,productRequest.quantity()));
        }
        return purchasedProducts;

    }

    public ProductResponse getProductById(Integer productId) {
        return repo.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public List<ProductResponse> getAllProducts() {
        return repo.findAll().stream()
                .map(product -> mapper.toProductResponse(product))
                .collect(Collectors.toList());
    }
}
