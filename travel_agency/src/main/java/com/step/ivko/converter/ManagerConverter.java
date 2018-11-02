package com.step.ivko.converter;

import com.step.ivko.model.Manager;
import com.step.ivko.web.dto.ManagerCreateDto;
import com.step.ivko.web.dto.ManagerViewDto;

public class ManagerConverter {
    public ManagerViewDto asManagerDto(Manager manager) {
        ManagerViewDto dto = new ManagerViewDto();
        dto.setId(manager.getId());
        dto.setName(manager.getName());
        dto.setSurname(manager.getSurname());
        dto.setAge(manager.getAge());
        dto.setEmail(manager.getEmail());
        dto.setIsManager(manager.isManager());
        dto.setIsBlocked(manager.isBlocked());
        return dto;
    }

    public Manager asManager(ManagerCreateDto createDto) {
        Manager manager = new Manager();
        manager.setId(createDto.getId());
        manager.setName(createDto.getName());
        manager.setSurname(createDto.getSurname());
        manager.setAge(createDto.getAge());
        manager.setEmail(createDto.getEmail());
        manager.setPassword(createDto.getPassword());
        manager.setIsManager(createDto.getIsManager());
        manager.setBlocked(createDto.getIsBlocked());
        return manager;
    }
}