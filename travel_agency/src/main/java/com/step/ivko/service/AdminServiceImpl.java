package com.step.ivko.service;


import com.step.ivko.converter.AdminConverter;
import com.step.ivko.dao.AdministratorDao;
import com.step.ivko.dao.AdministratorDaoImpl;
import com.step.ivko.model.Administrator;
import com.step.ivko.validator.UserValidator;
import com.step.ivko.validator.UserValidatorImpl;
import com.step.ivko.web.dto.AdminCreateDto;
import com.step.ivko.web.dto.AdminViewDto;

import java.util.List;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminService {
    private AdministratorDao adminDao = new AdministratorDaoImpl();
    private UserValidator userValidator = new UserValidatorImpl();
    private AdminConverter adminConverter = new AdminConverter();

    @Override
    public AdminViewDto registerAdmin(AdminCreateDto createDto) {
        userValidator.validateUser(createDto, true);
        Administrator administrator = adminConverter.asAdministrator(createDto);
        administrator = adminDao.create(administrator);
        return adminConverter.asAdministratorDto(administrator);
    }

    @Override
    public List<AdminViewDto> getAllAdmins() {
        List<Administrator> administrators = adminDao.getAll();
        return administrators
                .stream()
                .map(administrator -> adminConverter.asAdministratorDto(administrator))
                .collect(Collectors.toList());
    }

    @Override
    public AdminViewDto getAdminById(Integer id) {
        Administrator administrator = adminDao.getById(id);
        return adminConverter.asAdministratorDto(administrator);
    }

    @Override
    public AdminViewDto updateAdmin(AdminCreateDto createDto) {
        userValidator.validateUser(createDto, false);
        Administrator administrator = adminConverter.asAdministrator(createDto);
        administrator = adminDao.update(administrator);
        return adminConverter.asAdministratorDto(administrator);
    }

    @Override
    public void updateAdmins(List<AdminCreateDto> createDtoList) {
        createDtoList.forEach(adminCreateDto -> userValidator.validateUser(adminCreateDto, false));
        List<Administrator> administrators = createDtoList
                .stream()
                .map(adminCreateDto -> adminConverter.asAdministrator(adminCreateDto))
                .collect(Collectors.toList());
        adminDao.update(administrators);
    }

    @Override
    public void deleteAdmin(AdminCreateDto createDto) {
        Administrator administrator = adminConverter.asAdministrator(createDto);
        adminDao.deleteAdministrator(administrator);
    }
}