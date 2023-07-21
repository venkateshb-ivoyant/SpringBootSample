package com.ivoyant.springBootCrudDemo.repository;

import com.ivoyant.springBootCrudDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}