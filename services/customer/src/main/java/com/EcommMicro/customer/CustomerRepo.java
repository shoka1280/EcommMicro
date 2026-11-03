package com.EcommMicro.customer;

import com.EcommMicro.customer.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo  extends MongoRepository<Customer,String> {
}
