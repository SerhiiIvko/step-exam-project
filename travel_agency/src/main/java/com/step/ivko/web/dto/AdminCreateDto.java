package com.step.ivko.web.dto;

import java.io.Serializable;
import java.util.Objects;

public class AdminCreateDto extends ManagerCreateDto implements Serializable {
    private static final long serialVersionUID = 1281576529367099012L;
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private Boolean isAdmin;

    public AdminCreateDto(String name, String surname, Integer age, String email, String password,
                            Boolean isAdmin) {
        this(null, name, surname, age, email, password, isAdmin);
    }

    public AdminCreateDto(Integer id, String name, String surname, Integer age, String email, String password,
                            Boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Boolean getAdmin() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AdminCreateDto that = (AdminCreateDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(age, that.age) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(isAdmin, that.isAdmin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, surname, age, email, password, isAdmin);
    }
}