package com.ivoyant.springBootCrudDemo.repository;

import com.ivoyant.springBootCrudDemo.entity.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@SpringBootTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Order(1)
    @Rollback
    public void saveEmployeeTest() {

        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Rathod")
                .email("rameshrathod@gmail.com")
                .build();

        employeeRepository.save(employee);

        Assertions.assertThat(employee.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public void getEmployeeTest() {

        Employee employee = employeeRepository.findById(3L).get();

        Assertions.assertThat(employee.getId()).isEqualTo(3L);

    }

    @Test
    @Order(3)
    public void getListOfEmployeesTest() {

        List<Employee> employees = employeeRepository.findAll();

        Assertions.assertThat(employees.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = true)
    public void updateEmployeeTest() {
        Long id = 3L;
        Optional<Employee> employee = employeeRepository.findById(id);
        employee.get().setEmail("ramesh@gmail.com");
        Employee employeeUpdated = employeeRepository.save(employee.get());
        Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("ramesh@gmail.com");

    }

    @Test
    @Order(5)
    @Rollback(value = true)
    public void deleteEmployeeTest() {
        Long id = 3L;
        employeeRepository.deleteById(id);
        Assertions.assertThat(employeeRepository.findById(3L).isEmpty());

    }

}