package org.junit.test.dao;

import org.junit.test.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	public Employee findById(long id);

}
