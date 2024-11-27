package com.employeemanagement.employee_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeemanagement.employee_management_system.entity.Employee;
import com.employeemanagement.employee_management_system.exception.ResourceNotFoundException;
import com.employeemanagement.employee_management_system.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public String addEmployee(Employee employee) {
		employeeRepository.save(employee);
		return "Employee added successfully!";
	}

	public Employee getEmployeeById(int id) {
		return employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public String updateEmployee(int id, Employee updatedEmployee) {
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

		existingEmployee.setName(updatedEmployee.getName());
		existingEmployee.setEmail(updatedEmployee.getEmail());
		existingEmployee.setPhone(updatedEmployee.getPhone());
		existingEmployee.setDepartment(updatedEmployee.getDepartment());

		employeeRepository.save(existingEmployee);
		return "Employee updated successfully!";
	}

	
	public String deleteEmployee(int id) {
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

		employeeRepository.delete(existingEmployee);
		return "Employee deleted successfully!";
	}

}
