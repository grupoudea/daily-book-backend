package co.com.udea.employee.persistence.mapper;

import co.com.udea.employee.domain.model.Employee;
import co.com.udea.employee.persistence.entity.EmployeeEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(EmployeeEntity entity);

    @InheritInverseConfiguration
    @Mapping(target = "id", ignore = true)
    EmployeeEntity toEmployeeEntity(Employee employee);

    List<Employee> toEmployees(List<EmployeeEntity> list);
}
