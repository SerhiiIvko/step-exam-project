package com.step.ivko.converter;

import com.step.ivko.model.Customer;
import com.step.ivko.web.dto.CustomerCreateDto;
import com.step.ivko.web.dto.CustomerViewDto;

public class CustomerConverter {
    public CustomerViewDto asCustomerDto(Customer customer) {
        CustomerViewDto dto = new CustomerViewDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setSurname(customer.getSurname());
        dto.setAge(customer.getAge());
        dto.setEmail(customer.getEmail());
        dto.setBlocked(customer.isBlocked());
        return dto;
    }

    public Customer asCustomer(CustomerCreateDto createDto) {
        Customer customer = new Customer();
        customer.setId(createDto.getId());
        customer.setName(createDto.getName());
        customer.setSurname(createDto.getSurname());
        customer.setAge(createDto.getAge());
        customer.setEmail(createDto.getEmail());
        customer.setPassword(createDto.getPassword());
        customer.setBlocked(createDto.getIsBlocked());
        return customer;
    }
}