package com.step.ivko.web.dto;

import java.io.Serializable;
import java.util.Objects;

public class ManagerCreateDto extends UserCreateDto implements Serializable {
    private static final long serialVersionUID = 751014166558232361L;
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private Boolean isManager;
    private Boolean isBlocked;

    public ManagerCreateDto(){
    }

    public ManagerCreateDto(String name, String surname, Integer age, String email, String password,
                            Boolean isManager, Boolean isBlocked) {
        this(null, name, surname, age, email, password, isManager, isBlocked);
    }

    public ManagerCreateDto(Integer id, String name, String surname, Integer age, String email, String password,
                            Boolean isManager, Boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.isManager = isManager;
        this.isBlocked = isBlocked;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManagerCreateDto)) return false;
        if (!super.equals(o)) return false;
        ManagerCreateDto createDto = (ManagerCreateDto) o;
        return Objects.equals(id, createDto.id) &&
                Objects.equals(name, createDto.name) &&
                Objects.equals(surname, createDto.surname) &&
                Objects.equals(age, createDto.age) &&
                Objects.equals(email, createDto.email) &&
                Objects.equals(password, createDto.password) &&
                Objects.equals(isManager, createDto.isManager) &&
                Objects.equals(isBlocked, createDto.isBlocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, surname, age, email, password, isManager, isBlocked);
    }
}