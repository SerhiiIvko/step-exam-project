package com.step.ivko.model;

import java.io.Serializable;
import java.util.Objects;

public class Manager extends User implements Serializable {
    private static final long serialVersionUID = -6760554713351224634L;
    private Boolean isBlocked;

    public Manager() {
        super();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public boolean markTourAsHot() {
        return false;
    }

    public boolean markTourAsPaid() {
        return false;
    }

    public boolean markTourAsCanceled() {
        return false;
    }

    public int setDiscount() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(id, manager.id) &&
                Objects.equals(name, manager.name) &&
                Objects.equals(surname, manager.surname) &&
                Objects.equals(age, manager.age) &&
                Objects.equals(email, manager.email) &&
                Objects.equals(password, manager.password) &&
//                Objects.equals(isManager, manager.isManager) &&
                Objects.equals(isBlocked, manager.isBlocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, surname, age, email, password, isBlocked);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}