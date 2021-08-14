package com.longnguyenquy.service;

import java.util.List;

import com.longnguyenquy.entity.Customer;

public interface CustomerService {

	public List<Customer> findAll();
	
	public Customer findById(int id);
	
	public void save(Customer customer);
	
	public void deleteById(int id);
	
}
