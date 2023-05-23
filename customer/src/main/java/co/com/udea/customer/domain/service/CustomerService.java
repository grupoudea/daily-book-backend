package co.com.udea.customer.domain.service;

import co.com.udea.customer.domain.errorhandler.BadResponseHandler;
import co.com.udea.customer.domain.model.Customer;
import co.com.udea.customer.domain.model.enums.Responses;
import co.com.udea.customer.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Customer getCustomer(Long identification) {
        return repository.getCustomer(identification)
                .orElseThrow( () -> {
                    throw new BadResponseHandler(Responses.NOT_FOUND_ENTITY.getMensaje(),
                            Responses.NOT_FOUND_ENTITY.getCodigo(),
                            Responses.NOT_FOUND_ENTITY.getHttpStatus());
                });
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomer() {
        return repository.getAllCustomer()
                .orElseThrow( () -> {
                    throw new BadResponseHandler(Responses.NOT_FOUND_ENTITIES.getMensaje(),
                            Responses.NOT_FOUND_ENTITIES.getCodigo(),
                            Responses.NOT_FOUND_ENTITIES.getHttpStatus());
                });
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        return repository.save(customer)
                .orElseThrow( () -> {
                    throw new BadResponseHandler(Responses.NOT_SAVE_ENTITY.getMensaje(),
                            Responses.NOT_SAVE_ENTITY.getCodigo(),
                            Responses.NOT_SAVE_ENTITY.getHttpStatus());
                });
    }

    @Transactional
    public boolean deleteCustomer(Long identification) {
        return repository.getCustomer(identification).map(customer -> {
            repository.delete(identification);
            return true;
        }).orElseThrow( () -> {
            throw new BadResponseHandler(Responses.NOT_DELETE_ENTITY.getMensaje(),
                    Responses.NOT_DELETE_ENTITY.getCodigo(),
                    Responses.NOT_DELETE_ENTITY.getHttpStatus());
        });
    }

    @Transactional
    public Customer updateCustomer(Customer customer) {
        return repository.getCustomer(customer.getIdentification())
                .map(temp -> repository.update(customer).orElseThrow( () -> {
                    throw new BadResponseHandler(Responses.NOT_UPDATE_ENTITY.getMensaje(),
                            Responses.NOT_UPDATE_ENTITY.getCodigo(),
                            Responses.NOT_UPDATE_ENTITY.getHttpStatus());
                })).orElseThrow(() -> {
                    throw new BadResponseHandler(Responses.NOT_FOUND_ENTITY.getMensaje(),
                            Responses.NOT_FOUND_ENTITY.getCodigo(),
                            Responses.NOT_FOUND_ENTITY.getHttpStatus());
                });
    }


}
