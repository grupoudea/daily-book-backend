package co.com.udea.employee.web.controller;

import co.com.udea.employee.domain.model.Employee;
import co.com.udea.employee.domain.model.Mensaje;
import co.com.udea.employee.domain.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(EmployeeController.EMPLOYEE_URL)
public class EmployeeController {

    public static final String EMPLOYEE_URL = "/employee";
    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/{identification}")
    public ResponseEntity<Employee> getCustomer(@PathVariable Long identification) {
        return new ResponseEntity<>(service.getEmployee(identification), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllCustomers() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> saveCustomer(@RequestBody Employee employee) {
        return new ResponseEntity<>(service.saveEmployee(employee), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Employee> updateCustomer(@RequestBody Employee employee) {
        return new ResponseEntity<>(service.updateEmployee(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{identification}")
    public ResponseEntity<Mensaje> deleteCustomer(@PathVariable Long identification) {
        service.deleteEmployee(identification);
        return new ResponseEntity<>(new Mensaje( "001", "Employee deleted"), HttpStatus.OK);
    }
}
