package co.com.udea.customer.persistence.mapper;

import co.com.udea.customer.domain.model.Customer;
import co.com.udea.customer.persistence.entity.CustomerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerEntity entity);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    CustomerEntity toCustomerEntity(Customer customer);

    List<Customer> toCustomers(List<CustomerEntity> list);
}
