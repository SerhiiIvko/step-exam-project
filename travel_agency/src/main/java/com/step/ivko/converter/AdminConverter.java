package com.step.ivko.converter;

import com.step.ivko.model.Administrator;
import com.step.ivko.web.dto.AdminCreateDto;
import com.step.ivko.web.dto.AdminViewDto;

public class AdminConverter {
    public AdminViewDto asAdministratorDto(Administrator administrator) {
        AdminViewDto dto = new AdminViewDto();
        dto.setId(administrator.getId());
        dto.setName(administrator.getName());
        dto.setSurname(administrator.getSurname());
        dto.setAge(administrator.getAge());
        dto.setEmail(administrator.getEmail());
        dto.setAdmin(administrator.isAdmin());
        return dto;
    }

    public Administrator asAdministrator(AdminCreateDto createDto) {
        Administrator administrator = new Administrator();
        administrator.setId(createDto.getId());
        administrator.setName(createDto.getName());
        administrator.setSurname(createDto.getSurname());
        administrator.setAge(createDto.getAge());
        administrator.setEmail(createDto.getEmail());
        administrator.setPassword(createDto.getPassword());
        administrator.setAdmin(createDto.getAdmin());
        return administrator;
    }
}