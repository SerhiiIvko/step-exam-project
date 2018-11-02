package com.step.ivko.dao;

import com.step.ivko.model.Manager;

import java.util.List;

public interface ManagerDao {
    Manager getById(Integer id);

    Manager getByEmail(String email);

    List<Manager> getAll();

    Manager create(Manager manager);

    Manager update(Manager manager);

    void update(List<Manager> managers);

    void deleteManager(Manager manager);
}