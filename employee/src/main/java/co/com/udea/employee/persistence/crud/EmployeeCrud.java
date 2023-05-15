package co.com.udea.employee.persistence.crud;

import co.com.udea.employee.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeCrud extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findEmployeeEntityByIdentification (Long identification);

    void deleteEmployeeEntityByIdentification (Long identification);
}
