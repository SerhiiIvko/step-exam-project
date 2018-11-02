package com.step.ivko.web.dto;

import java.io.Serializable;
import java.util.Objects;

public class CustomerCreateDto extends UserCreateDto implements Serializable {
    private static final long serialVersionUID = -3197527088022305924L;
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private Boolean isBlocked;

    public CustomerCreateDto(String name, String surname, Integer age, String email, String password, Boolean isBlocked) {
        this(null, name, surname, age, email, password, isBlocked);
    }

    public CustomerCreateDto(Integer id, String name, String surname, Integer age, String email, String password,
                             Boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
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

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomerCreateDto that = (CustomerCreateDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(age, that.age) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(isBlocked, that.isBlocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, surname, age, email, password, isBlocked);
    }
}