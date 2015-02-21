package junit.test.web.controller;

import org.junit.test.core.api.EmployeeService;
import org.junit.test.model.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/hello")
	public String hello(@RequestParam(value="name", required=false, defaultValue="world") String name, Model model){
		model.addAttribute("name", name);
		System.out.println(employeeService.removeEmployee(Employee.class, 1l));
		return "helloworld";
	}
}
