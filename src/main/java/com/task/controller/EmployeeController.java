package com.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.RequestDetails;
import com.task.ResponseDetails;
import com.task.employee.EmployeeWrapper;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeWrapper employeeWrapper;
	
	@PostMapping("/addorUpdateEmployee")
	public ResponseDetails addorUpdateEmployee(@RequestBody RequestDetails requestDetails) {
		return employeeWrapper.addEmployee(requestDetails);
	}
	
	@GetMapping("/listOfEmployees")
	public ResponseDetails getAllProduct(@RequestBody RequestDetails requestDetails) {
		return employeeWrapper.listOfEmployees(requestDetails);
	}
	
   @GetMapping("/getById")
   public ResponseDetails getById(@RequestBody RequestDetails requestDetails) {
		return employeeWrapper.getById(requestDetails);
	}
	
   @GetMapping("/deleteById")
   public ResponseDetails deleteById(@RequestBody RequestDetails requestDetails) {
	    		return employeeWrapper.deleteById(requestDetails);

   }

   @PostMapping("/sendWelcomeMessage/{employeeId}")
   public ResponseDetails sendWelcomeMessage(@PathVariable String employeeId) {
	   return employeeWrapper.sendWelcomeMessage(employeeId);
   }

   
	
}
