package com.example.spring.mockito.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.mockito.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
