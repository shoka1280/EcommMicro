package com.EcommMicro.order.order;


import com.EcommMicro.customer.CustomerClient;
import com.EcommMicro.exception.BusinessException;
import com.EcommMicro.kafka.OrderConfirmation;
import com.EcommMicro.kafka.OrderProducer;
import com.EcommMicro.order.orderline.OrderLineRequest;
import com.EcommMicro.order.orderline.OrderLineService;
import com.EcommMicro.payment.PaymentClient;
import com.EcommMicro.payment.PaymentRequest;
import com.EcommMicro.product.ProductClient;
import com.EcommMicro.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRespository respository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

//    private final ProductClient productClient;
    public Integer createOrder(OrderRequest orderRequest) {
        //check customer exists in customer service -->open feign client(CUSTOMER MICROSERVICE)

        var customer=this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(()->new BusinessException("Cannnot order :No customer found"));
        // purchase the product(PRODUCT MICROSERVICE) (Rest template)
       var purchasedProducts= this.productClient.purchaseProducts(orderRequest.products());

        //persisit order
        var order=this.respository.save(mapper.toOrder(orderRequest));
        //persisit order lines
        for(PurchaseRequest purchaseRequest:orderRequest.products())
        {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()

                    )
            );

            return order.getId();

        }
        // start payement process
        var paymentRequest=new PaymentRequest(orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                orderRequest.reference(),
               customer
                );
        paymentClient.requestOrderPayment(paymentRequest);
        // send order confirmation->NOTIFICATION SERVICE(KAFKA)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts

                )
        );


        return order.getId();
    }

    public List<OrderResponse> getAllOrders() {
        return respository.findAll().stream().map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrderById(Integer id) {
        return respository.findById(id).map(mapper::fromOrder)
                .orElseThrow(()->new EntityNotFoundException(String.format("Order not found: %d",id)));
    }
}
