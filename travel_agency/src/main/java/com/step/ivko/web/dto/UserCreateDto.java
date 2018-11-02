package com.step.ivko.web.dto;

import java.io.Serializable;
import java.util.Objects;

public abstract class UserCreateDto implements Serializable {
    private static final long serialVersionUID = 8659613407362338537L;
    protected Integer id;
    protected String name;
    protected String surname;
    protected Integer age;
    protected String email;
    protected String password;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateDto that = (UserCreateDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(age, that.age) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, age, email, password);
    }
}