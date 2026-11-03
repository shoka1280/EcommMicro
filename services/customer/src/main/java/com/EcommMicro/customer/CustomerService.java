package com.EcommMicro.customer;

import com.EcommMicro.customer.customer.Customer;
import com.EcommMicro.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public String creatCustomer(@Valid CustomerRequest request) {
        Customer customer = customerRepo.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        Customer customer = customerRepo.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with id %s", request.id())));
        mergeCustomer(customer, request);
        customerRepo.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
        if (request.firstname() != null) {
            customer.setFirstname(request.firstname());
        }
        if (request.lastname() != null) {
            customer.setLastname(request.lastname());
        }
        if (request.email() != null) {
            customer.setEmail(request.email());
        }
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepo.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .toList();
    }

    public Boolean existsById(String id) {
        return customerRepo.existsById(id);
    }

    public CustomerResponse findById(String id) {
        return customerRepo.findById(id)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("No customer found with id %s", id)));
    }

    public void deleteById(String id) {
        customerRepo.deleteById(id);
    }
}
