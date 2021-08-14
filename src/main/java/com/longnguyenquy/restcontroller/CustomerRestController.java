package com.longnguyenquy.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longnguyenquy.entity.Customer;
import com.longnguyenquy.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	private CustomerService customerService;
	
	@Autowired
	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	
	@GetMapping("/customers")
	public List<Customer> findAll() {
		
		List<Customer> customers = customerService.findAll();
		System.out.println(customers);
		return customers;
		
	}


	
	@GetMapping("/customers/{id}")
	public Customer getEmployee(@PathVariable int id) {
		
		Customer theEmployee = customerService.findById(id);
		
		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + id);
		}
		
		return theEmployee;
	}
	
	
	
	@PostMapping("/customers")
	public Customer addEmployee(@RequestBody Customer customer) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		customer.setId(0);
		
		customerService.save(customer);
		
		return customer;
	}
	
	
	
	@PutMapping("/customers")
	public Customer updateEmployee(@RequestBody Customer customers) {
		
		customerService.save(customers);
		
		return customers;
	}
	
	
	
	@DeleteMapping("/customers/{id}")
	public String deleteEmployee(@PathVariable int id) {
		
		Customer customer = customerService.findById(id);
		
		// throw exception if null
		
		if (customer == null) {
			throw new RuntimeException("Customer id not found - " + id);
		}
		
		customerService.deleteById(id);
		
		return "Deleted employee id - " + id;
	}
	
}









