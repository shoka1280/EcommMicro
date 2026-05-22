package com.ecommmicro.product.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    @PostMapping()
    public ResponseEntity<Integer>createProduct(@Valid @RequestBody
                                                ProductRequest request)
    {
        Integer productId=service.createProduct(request);
        return ResponseEntity.ok(productId);
    }
    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody List<ProductPurchaseRequest> purchaseRequests
    ) {

        return ResponseEntity.ok(service.purchaseProducts(purchaseRequests));
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer productId){

        return ResponseEntity.ok(service.getProductById(productId));
    }
    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProduct() {

        return ResponseEntity.ok(service.getAllProducts());
    }

}
