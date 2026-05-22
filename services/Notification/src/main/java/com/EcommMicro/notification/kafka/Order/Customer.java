package com.EcommMicro.notification.kafka.Order;

public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {

}
