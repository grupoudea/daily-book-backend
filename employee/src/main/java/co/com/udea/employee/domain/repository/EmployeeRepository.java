package co.com.udea.employee.domain.repository;


import co.com.udea.employee.domain.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Optional<Employee> save (Employee customer);
    Optional<Employee> getEmployee(Long identification);
    Optional<List<Employee>> getAllEmployee();
    void delete(Long identification);
    Optional<Employee> update(Employee customer);
}
