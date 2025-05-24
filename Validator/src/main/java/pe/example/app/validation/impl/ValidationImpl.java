package pe.example.app.validation.impl;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.example.app.dto.UserDTO;
import pe.example.app.validation.Validation;
import pe.example.app.validation.handler.ValidationException;

@Service
@RequiredArgsConstructor
public class ValidationImpl implements Validation {

    private final Validator validator;

    @Override
    public UserDTO validateUserDto(UserDTO userDTO) {
        var errors = validator.validate(userDTO);
        if(!errors.isEmpty()) throw new ValidationException(errors);
        return userDTO;
    }
}
