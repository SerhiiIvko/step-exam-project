package com.step.ivko.dao;

import com.step.ivko.model.Administrator;

import java.util.List;

public interface AdministratorDao {
    Administrator getById(Integer id);

    Administrator getByEmail(String email);

    List<Administrator> getAll();

    Administrator create(Administrator administrator);

    Administrator update(Administrator administrator);

    void update(List<Administrator> administrators);

    void deleteAdministrator(Administrator administrator);
}