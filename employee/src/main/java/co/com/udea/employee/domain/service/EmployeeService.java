package co.com.udea.employee.domain.service;

import co.com.udea.employee.domain.errorhandler.BadResponseHandler;
import co.com.udea.employee.domain.model.Employee;
import co.com.udea.employee.domain.model.enums.Responses;
import co.com.udea.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Employee getEmployee(Long identification) {
        return repository.getEmployee(identification)
                .orElseThrow( () -> {
                    throw new BadResponseHandler(Responses.NOT_FOUND_ENTITY.getMensaje(),
                            Responses.NOT_FOUND_ENTITY.getCodigo(),
                            Responses.NOT_FOUND_ENTITY.getHttpStatus());
                });
    }

    @Transactional(readOnly = true)
    public List<Employee> getAll() {
        return repository.getAllEmployee()
                .orElseThrow( () -> {
                    throw new BadResponseHandler(Responses.NOT_FOUND_ENTITIES.getMensaje(),
                            Responses.NOT_FOUND_ENTITIES.getCodigo(),
                            Responses.NOT_FOUND_ENTITIES.getHttpStatus());
                });
    }

    @Transactional
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee)
                .orElseThrow( () -> {
                    throw new BadResponseHandler(Responses.NOT_SAVE_ENTITY.getMensaje(),
                            Responses.NOT_SAVE_ENTITY.getCodigo(),
                            Responses.NOT_SAVE_ENTITY.getHttpStatus());
                });
    }

    @Transactional
    public boolean deleteEmployee(Long identification) {
        return repository.getEmployee(identification).map(customer -> {
            repository.delete(identification);
            return true;
        }).orElseThrow( () -> {
            throw new BadResponseHandler(Responses.NOT_DELETE_ENTITY.getMensaje(),
                    Responses.NOT_DELETE_ENTITY.getCodigo(),
                    Responses.NOT_DELETE_ENTITY.getHttpStatus());
        });
    }

    @Transactional
    public Employee updateEmployee(Employee employee) {
        return repository.getEmployee(employee.getIdentification())
                .map(temp -> repository.update(employee).orElseThrow( () -> {
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
