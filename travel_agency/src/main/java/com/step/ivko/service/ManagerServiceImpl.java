package com.step.ivko.service;

import com.step.ivko.converter.ManagerConverter;
import com.step.ivko.dao.ManagerDao;
import com.step.ivko.dao.ManagerDaoImpl;
import com.step.ivko.model.Manager;
import com.step.ivko.validator.UserValidator;
import com.step.ivko.validator.UserValidatorImpl;
import com.step.ivko.web.dto.ManagerCreateDto;
import com.step.ivko.web.dto.ManagerViewDto;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerServiceImpl implements ManagerService {
    private ManagerDao managerDao = new ManagerDaoImpl();
    private UserValidator userValidator = new UserValidatorImpl();
    private ManagerConverter managerConverter = new ManagerConverter();

    @Override
    public ManagerViewDto registerManager(ManagerCreateDto createDto) {
        userValidator.validateUser(createDto, true);
        Manager manager = managerConverter.asManager(createDto);
        manager = managerDao.create(manager);
        return managerConverter.asManagerDto(manager);
    }

    public ManagerViewDto updateManager(ManagerCreateDto createDto) {
        userValidator.validateUser(createDto, false);
        Manager manager = managerConverter.asManager(createDto);
        manager = managerDao.update(manager);
        return managerConverter.asManagerDto(manager);
    }

    @Override
    public void updateManagers(List<ManagerCreateDto> createDtoList) {
        createDtoList.forEach(managerCreateDto -> userValidator.validateUser(managerCreateDto, false));
        List<Manager> managers = createDtoList
                .stream()
                .map(managerCreateDto -> managerConverter.asManager(managerCreateDto))
                .collect(Collectors.toList());
        managerDao.update(managers);
    }

    @Override
    public void deleteManager(ManagerCreateDto createDto) {
        Manager manager = managerConverter.asManager(createDto);
        managerDao.deleteManager(manager);
    }

    @Override
    public List<ManagerViewDto> getAllManagers() {
        List<Manager> managers = managerDao.getAll();
        return managers
                .stream()
                .map(manager -> managerConverter.asManagerDto(manager))
                .collect(Collectors.toList());
    }

    @Override
    public ManagerViewDto getManagerById(Integer id) {
        Manager manager = managerDao.getById(id);
        return managerConverter.asManagerDto(manager);
    }
}