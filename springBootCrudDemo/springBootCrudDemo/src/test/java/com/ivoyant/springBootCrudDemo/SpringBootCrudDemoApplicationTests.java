
package com.ivoyant.springBootCrudDemo;

import com.ivoyant.springBootCrudDemo.entity.Employee;
import com.ivoyant.springBootCrudDemo.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringBootCrudDemoApplicationTests {
    @Autowired
    EmployeeService employeeService;

    @Test
    public void testEmployeeById() {
        Long userId = 2L;
        Employee employee = employeeService.getEmployeeById(userId);
        assertEquals(userId, employee.getId());
    }
}

