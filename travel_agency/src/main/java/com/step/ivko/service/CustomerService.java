package com.step.ivko.service;

import com.step.ivko.web.dto.CustomerCreateDto;
import com.step.ivko.web.dto.CustomerViewDto;

import java.util.List;

public interface CustomerService {
    CustomerViewDto registerCustomer(CustomerCreateDto createDto);
    List<CustomerViewDto> getAllCustomers();
    CustomerViewDto getCustomerById(Integer id);
    CustomerViewDto updateCustomer(CustomerCreateDto createDto);
    void updateCustomers(List<CustomerCreateDto> createDtoList);
    void deleteCustomer(CustomerCreateDto createDto);
}