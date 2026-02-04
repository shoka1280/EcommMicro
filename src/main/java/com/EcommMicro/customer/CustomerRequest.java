package com.EcommMicro.customer;

import com.EcommMicro.customer.customer.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,
         @NotNull(message = "firstname cannot be null")
         String firstname,
         @NotNull(message = "lastname cannot be null")
         String lastname,
         @Email(message = "Not valid email")
         String email,
         Address address
) {
}
