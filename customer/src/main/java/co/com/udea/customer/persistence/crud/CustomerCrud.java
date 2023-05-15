package co.com.udea.customer.persistence.crud;

import co.com.udea.customer.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerCrud extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findCustomerEntityByIdentification(Long identification);

    void deleteCustomerEntityByIdentification(Long identification);
}
