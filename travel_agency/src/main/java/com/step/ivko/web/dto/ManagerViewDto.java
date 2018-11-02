package com.step.ivko.web.dto;

import java.io.Serializable;
import java.util.Objects;

public class ManagerViewDto implements Serializable {
    private static final long serialVersionUID = 751014166558267361L;
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private Boolean isManager;
    private Boolean isBlocked;

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean manager) {
        isManager = manager;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManagerViewDto)) return false;
        ManagerViewDto viewDto = (ManagerViewDto) o;
        return Objects.equals(id, viewDto.id) &&
                Objects.equals(name, viewDto.name) &&
                Objects.equals(surname, viewDto.surname) &&
                Objects.equals(age, viewDto.age) &&
                Objects.equals(email, viewDto.email) &&
                Objects.equals(isManager, viewDto.isManager) &&
                Objects.equals(isBlocked, viewDto.isBlocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, email, isManager, isBlocked);
    }
}