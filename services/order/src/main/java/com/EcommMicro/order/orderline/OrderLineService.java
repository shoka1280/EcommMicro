package com.EcommMicro.order.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository respository;
    private final OrderLineMapper mapper;
    public Integer saveOrderLine(OrderLineRequest request) {
        var order=mapper.toOrderLine(request);
        return respository.save(order).getId();


    }



    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return respository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());

    }
}
