package com.longnguyenquy.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.longnguyenquy.entity.Customer;


public class CustomerDAOJpaImpl implements CustomerDAO {

	private EntityManager entityManager;
	
	@Autowired
	public CustomerDAOJpaImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Customer> findAll() {

		// create a query
		Query theQuery = 
				entityManager.createQuery("from Customer");
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// return the results		
		return customers;
	}

	@Override
	public Customer findById(int id) {

		// get employee
		Customer customer = 
				entityManager.find(Customer.class, id);
		
		// return employee
		return customer;
	}

	@Override
	public void save(Customer employee) {

		// save or update the employee
		Customer dbEmployee = entityManager.merge(employee);
		
		// update with id from db ... so we can get generated id for save/insert
		employee.setId(dbEmployee.getId());
		
	}

	@Override
	public void deleteById(int id) {

		// delete object with primary key
		Query theQuery = entityManager.createQuery(
							"delete from Customer where id=:id");
		
		theQuery.setParameter("id", id);
		
		theQuery.executeUpdate();
	}

}










