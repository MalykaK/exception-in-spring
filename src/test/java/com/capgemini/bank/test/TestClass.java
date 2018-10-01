package com.capgemini.bank.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.bank.controller.CustomerController;
import com.capgemini.bank.exceptions.UserNotFoundException;
import com.capgemini.bank.model.BankAccount;
import com.capgemini.bank.model.Customer;
import com.capgemini.bank.repository.CustomerRepository;

public class TestClass {
	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerRepository customerRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAuthenticate() {
		Customer customer = new Customer();
		customer.setCustomerId(12345);
		customer.setPassword("12");

		Customer customer1 = new Customer("Malyka", 1234, "1234", "malyka78@gmail.com", "hyderabad",
				LocalDate.of(1996, 8, 18), new BankAccount(22222, 123, "SAVINGS"));

		try {
			when(customerRepository.authenticate(customer)).thenReturn(customer1);
		} catch (UserNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try {
			assertEquals(customer1, customerRepository.authenticate(customer));
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
