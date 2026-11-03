package com.EcommMicro.customer;

import com.EcommMicro.customer.customer.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    public final CustomerService customerService;
    @PostMapping()
    public ResponseEntity<String> createCustomer(
            @Valid @RequestBody CustomerRequest request)
    {
        return ResponseEntity.ok(customerService.creatCustomer(request));

    }
    @PutMapping()
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerRequest request){
        customerService.updateCustomer(request);
        return ResponseEntity.ok().build();
    }
    @GetMapping()
    public ResponseEntity<List<CustomerResponse> > getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @GetMapping("/exits/{custId}")
    public ResponseEntity<Boolean> exitA(@PathVariable("custId") String id){
        return ResponseEntity.ok(customerService.existsById(id));
    }
    @GetMapping("/{custId}")
    public ResponseEntity<CustomerResponse> findByid(@PathVariable("custId") String id){
        return ResponseEntity.ok(customerService.findById(id));
    }
    @DeleteMapping("/{custId}")
    public ResponseEntity<Void> delByid(@PathVariable("custId") String id){
        customerService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/hi")
    public String hi(){

        return "Hi qt pie";
    }
}
