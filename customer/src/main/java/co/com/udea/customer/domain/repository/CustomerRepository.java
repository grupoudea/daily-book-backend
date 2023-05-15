package co.com.udea.customer.domain.repository;

import co.com.udea.customer.domain.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> save (Customer customer);
    Optional<Customer> getCustomer(Long identification);
    Optional<List<Customer>> getAllCustomer();
    void delete(Long identification);
    Optional<Customer> update(Customer customer);
}
