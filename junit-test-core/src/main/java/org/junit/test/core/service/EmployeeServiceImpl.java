package org.junit.test.core.service;

import org.junit.test.core.api.EmployeeService;
import org.junit.test.dao.EmployeeRepository;
import org.junit.test.model.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public boolean removeEmployee(Class<?>type, long id) {
		for(Employee empl: employeeRepository.findAll()){
			System.out.println(empl);
		}
		return false;
	}

}
