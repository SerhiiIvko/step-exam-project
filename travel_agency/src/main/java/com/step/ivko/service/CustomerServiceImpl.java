package com.step.ivko.service;

import com.step.ivko.converter.CustomerConverter;
import com.step.ivko.dao.CustomerDao;
import com.step.ivko.dao.CustomerDaoImpl;
import com.step.ivko.model.Customer;
import com.step.ivko.validator.UserValidator;
import com.step.ivko.validator.UserValidatorImpl;
import com.step.ivko.web.dto.CustomerCreateDto;
import com.step.ivko.web.dto.CustomerViewDto;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService{
    private CustomerDao customerDao = new CustomerDaoImpl();
    private UserValidator userValidator = new UserValidatorImpl();
    private CustomerConverter customerConverter = new CustomerConverter();

    @Override
    public CustomerViewDto registerCustomer(CustomerCreateDto createDto) {
        userValidator.validateUser(createDto, true);
        Customer customer = customerConverter.asCustomer(createDto);
        customer = customerDao.create(customer);
        return customerConverter.asCustomerDto(customer);
    }

    @Override
    public List<CustomerViewDto> getAllCustomers() {
        List<Customer> customers = customerDao.getAll();
        return customers
                .stream()
                .map(customer -> customerConverter.asCustomerDto(customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerViewDto getCustomerById(Integer id) {
        Customer customer = customerDao.getById(id);
        return customerConverter.asCustomerDto(customer);
    }

    @Override
    public CustomerViewDto updateCustomer(CustomerCreateDto createDto) {
        userValidator.validateUser(createDto, false);
        Customer customer = customerConverter.asCustomer(createDto);
        customer = customerDao.update(customer);
        return customerConverter.asCustomerDto(customer);
    }

    @Override
    public void updateCustomers(List<CustomerCreateDto> createDtoList) {
        createDtoList.forEach(customerCreateDto -> userValidator.validateUser(customerCreateDto, false));
        List<Customer> customers = createDtoList
                .stream()
                .map(customerCreateDto -> customerConverter.asCustomer(customerCreateDto))
                .collect(Collectors.toList());
        customerDao.update(customers);
    }

    @Override
    public void deleteCustomer(CustomerCreateDto createDto) {
        Customer customer = customerConverter.asCustomer(createDto);
        customerDao.deleteCustomer(customer);
    }
}