package com.step.ivko.validator;

import com.step.ivko.exception.ValidationException;
import com.step.ivko.web.dto.UserCreateDto;
import org.apache.commons.lang3.StringUtils;

public class UserValidatorImpl implements UserValidator {

    @Override
    public void validateUserCredentials(String email, String password, boolean create) {
        if (StringUtils.isEmpty(email) || (create && StringUtils.isEmpty(password))) {
            throw new ValidationException("Invalid credentials: " + email);
        }
    }

    @Override
    public void validateUser(UserCreateDto createDto, boolean create) {
        validateUserCredentials(createDto.getEmail(), createDto.getPassword(), create);
        if (StringUtils.isEmpty(createDto.getName())) {
            throw new ValidationException("Invalid manager name: " + createDto.getName());
        }
    }
}