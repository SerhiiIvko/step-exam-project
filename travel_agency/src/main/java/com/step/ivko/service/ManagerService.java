package com.step.ivko.service;

import com.step.ivko.web.dto.ManagerCreateDto;
import com.step.ivko.web.dto.ManagerViewDto;

import java.util.List;

public interface ManagerService {
    ManagerViewDto registerManager(ManagerCreateDto createDto);
    List<ManagerViewDto> getAllManagers();
    ManagerViewDto getManagerById(Integer id);
    ManagerViewDto updateManager(ManagerCreateDto createDto);
    void updateManagers(List<ManagerCreateDto> createDtoList);
    void deleteManager(ManagerCreateDto createDto);
}