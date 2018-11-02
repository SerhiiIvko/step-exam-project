package com.step.ivko.service;


import com.step.ivko.web.dto.AdminCreateDto;
import com.step.ivko.web.dto.AdminViewDto;

import java.util.List;

public interface AdminService {
    AdminViewDto registerAdmin(AdminCreateDto createDto);
    List<AdminViewDto> getAllAdmins();
    AdminViewDto getAdminById(Integer id);
    AdminViewDto updateAdmin(AdminCreateDto createDto);
    void updateAdmins(List<AdminCreateDto> createDtoList);
    void deleteAdmin(AdminCreateDto createDto);
}