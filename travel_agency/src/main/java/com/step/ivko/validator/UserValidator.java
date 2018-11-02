package com.step.ivko.validator;


import com.step.ivko.web.dto.UserCreateDto;

public interface UserValidator {
    void validateUserCredentials(String email, String password, boolean create);
    void validateUser(UserCreateDto createDto, boolean create);
}