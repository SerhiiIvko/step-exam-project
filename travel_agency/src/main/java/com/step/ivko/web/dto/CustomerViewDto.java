package com.step.ivko.web.dto;

import java.io.Serializable;
import java.util.Objects;

public class CustomerViewDto implements Serializable {
    private static final long serialVersionUID = 8071786572870120705L;
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;
    private Boolean isBlocked;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerViewDto that = (CustomerViewDto) o;
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
        return Objects.hash(id, name, surname, age, email, password, isBlocked);
    }
}