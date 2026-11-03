package com.EcommMicro.customer;

import com.EcommMicro.customer.customer.Address;

public record CustomerResponse (
         String id,
         String firstname,
         String lastname,
         String email,
         Address address
) {
}
