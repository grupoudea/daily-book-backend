package co.com.udea.employee.persistence.repositoryimpl;

import co.com.udea.employee.domain.model.Employee;
import co.com.udea.employee.domain.repository.EmployeeRepository;
import co.com.udea.employee.persistence.crud.EmployeeCrud;
import co.com.udea.employee.persistence.entity.EmployeeEntity;
import co.com.udea.employee.persistence.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EmployeeCrud persistence;
    private final EmployeeMapper mapper;

    @Autowired
    public EmployeeRepositoryImpl(EmployeeCrud persistence, EmployeeMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public Optional<Employee> save(Employee customer) {
        Optional<EmployeeEntity> entity = persistence.findEmployeeEntityByIdentification(customer.getIdentification());

        return entity.isPresent()?
                Optional.empty():
                Optional.of(mapper.toEmployee(persistence.save(mapper.toEmployeeEntity(customer))));
    }

    @Override
    public Optional<Employee> getEmployee(Long identification) {
        return Optional.of(mapper.toEmployee(persistence.findEmployeeEntityByIdentification(identification)
                .orElse(null)));
    }

    @Override
    public Optional<List<Employee>> getAllEmployee() {
        return Optional.of(mapper.toEmployees(persistence.findAll()));
    }

    @Override
    public void delete(Long identification) {
        persistence.deleteEmployeeEntityByIdentification(identification);
    }

    @Override
    public Optional<Employee> update(Employee employee) {
        Optional<EmployeeEntity> entity = persistence.findEmployeeEntityByIdentification(employee.getIdentification());

        if (entity.isEmpty()) {
            return Optional.empty();
        }
        EmployeeEntity temp = mapper.toEmployeeEntity(employee);
        temp.setId(entity.get().getId());

        return Optional.of(mapper.toEmployee(persistence.save(temp)));
    }
}
