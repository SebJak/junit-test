package org.junit.test.dao;

import org.junit.test.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	public Department findById(long id);
}
