package com.EcommMicro.customer;

import com.EcommMicro.customer.customer.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.aot.MongoAotRepositoryFragmentSupport;

public interface CustomerRepo  extends MongoRepository<Customer,String> {
}
