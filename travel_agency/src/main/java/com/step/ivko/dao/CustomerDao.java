package com.step.ivko.dao;

import com.step.ivko.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer getById(Integer id);

    Customer getByEmail(String email);

    List<Customer> getAll();

    Customer create(Customer customer);

    Customer update(Customer customer);

    void update(List<Customer> customers);

    void deleteCustomer(Customer customer);
}