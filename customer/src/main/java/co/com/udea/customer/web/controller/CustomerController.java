package co.com.udea.customer.web.controller;

import co.com.udea.customer.domain.model.Customer;
import co.com.udea.customer.domain.model.Mensaje;
import co.com.udea.customer.domain.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CustomerController.CUSTOMER_URL)
public class CustomerController {

    public static final String CUSTOMER_URL = "/customer";
    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/{identification}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long identification) {
        return new ResponseEntity<>(service.getCustomer(identification), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(service.getAllCustomer(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(service.saveCustomer(customer), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(service.updateCustomer(customer), HttpStatus.OK);
    }

    @DeleteMapping("/{identification}")
    public ResponseEntity<Mensaje> deleteCustomer(@PathVariable Long identification) {
        service.deleteCustomer(identification);
        return new ResponseEntity<>(new Mensaje( "001", "Customer deleted"), HttpStatus.OK);
    }
}
