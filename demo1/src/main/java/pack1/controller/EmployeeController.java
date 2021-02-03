package pack1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pack1.exception.ResourceNotFoundException;
import pack1.model.Employee;
import pack1.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	//create all employee API
	@GetMapping("/emp")
			public List<Employee> getAllEmployee()
			{
				return employeeRepository.findAll();
			}

	//create employee
		@PostMapping("/emp")
		public Employee createEmployee( @Validated @RequestBody Employee employee)
		{
			return employeeRepository.save(employee);
		}
		//get employee by id
		@GetMapping("/emp/{id}")
		public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
				throws ResourceNotFoundException {
			Employee employee = employeeRepository.findById(employeeId)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
			return ResponseEntity.ok().body(employee);
		}
		//update employee
		@PutMapping("/emp/{id}")
		public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
				@Validated @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
			Employee employee = employeeRepository.findById(employeeId)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

			employee.setEmailid(employeeDetails.getEmailid());
			employee.setLastname(employeeDetails.getLastname());
			employee.setFirstname(employeeDetails.getFirstname());
			final Employee updatedEmployee = employeeRepository.save(employee);
			return ResponseEntity.ok(updatedEmployee);
		}
		//delete employee by id
		@DeleteMapping("/emp/{id}")
		public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
				throws ResourceNotFoundException {
			Employee employee = employeeRepository.findById(employeeId)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

			employeeRepository.delete(employee);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}

}

