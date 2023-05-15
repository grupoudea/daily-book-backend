package co.com.udea.customer.persistence.repositoryimpl;

import co.com.udea.customer.domain.model.Customer;
import co.com.udea.customer.domain.repository.CustomerRepository;
import co.com.udea.customer.persistence.crud.CustomerCrud;
import co.com.udea.customer.persistence.entity.CustomerEntity;
import co.com.udea.customer.persistence.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerCrud persistence;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerRepositoryImpl(CustomerCrud persistence, CustomerMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public Optional<Customer> save(Customer customer) {
        Optional<CustomerEntity> entity = persistence.findCustomerEntityByIdentification(customer.getIdentification());

        return entity.isPresent()?
                Optional.empty():
                Optional.of(mapper.toCustomer(persistence.save(mapper.toCustomerEntity(customer))));
    }

    @Override
    public Optional<Customer> getCustomer(Long identification) {
        return Optional.of(mapper.toCustomer(persistence.findCustomerEntityByIdentification(identification)
                .orElse(null)));
    }

    @Override
    public Optional<List<Customer>> getAllCustomer() {
        return Optional.of(mapper.toCustomers(persistence.findAll()));
    }

    @Override
    public void delete(Long identification) {
        persistence.deleteCustomerEntityByIdentification(identification);
    }

    @Override
    public Optional<Customer> update(Customer customer) {
        Optional<CustomerEntity> entity = persistence.findCustomerEntityByIdentification(customer.getIdentification());

        if (entity.isEmpty()) {
            return Optional.empty();
        }
        CustomerEntity temp = mapper.toCustomerEntity(customer);
        temp.setId(entity.get().getId());

        return Optional.of(mapper.toCustomer(persistence.save(temp)));
    }
}
