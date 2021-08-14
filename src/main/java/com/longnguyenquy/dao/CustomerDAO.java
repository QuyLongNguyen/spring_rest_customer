package com.longnguyenquy.dao;

import java.util.List;

import com.longnguyenquy.entity.Customer;

public interface CustomerDAO {

	public List<Customer> findAll();
	
	public Customer findById(int id);
	
	public void save(Customer customer);
	
	public void deleteById(int id);
	
}
