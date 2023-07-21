package com.ivoyant.springBootCrudDemo.service.Implement;

import com.ivoyant.springBootCrudDemo.entity.Employee;
import com.ivoyant.springBootCrudDemo.repository.EmployeeRepository;
import com.ivoyant.springBootCrudDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


@Service
//@AllArgsConstructor
public class EmployeeServiceImplementation implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImplementation.class);

    @Override
    public Employee createEmployee(Employee employee) {
        try {
            logger.info("Sucessfully inserted data for name {} {}", employee.getFirstName(), employee.getLastName());
            return employeeRepository.save(employee);
        } catch (Exception e) {
            logger.error("Exception occurred{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        try {
            Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
            logger.info("Sucessfully fetched data for ID {}", employeeId);
            return optionalEmployee.get();
        } catch (Exception e) {
            logger.error("Exception occurred{}", e.getMessage());
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            logger.info("Sucessfully fetched all the data from Employee ");
            return employeeRepository.findAll();

        } catch (Exception e) {
            logger.error("Exception occurred{}", e.getMessage());
        }
        return null;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        try {
            Employee existingEmployee = employeeRepository.findById(employee.getId()).get();
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            Employee updatedEmployee = employeeRepository.save(existingEmployee);
            logger.info("Updated data for employee {} {}", employee.getFirstName(), employee.getLastName());
            return updatedEmployee;

        } catch (Exception e) {
            logger.error("Exception occurred{}", e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        try {
            employeeRepository.deleteById(employeeId);
            logger.info("Sucessfully Deleted data for ID {}", employeeId);
        } catch (Exception e) {
            logger.error("Exception occurred{}", e.getMessage());
        }
    }
}
