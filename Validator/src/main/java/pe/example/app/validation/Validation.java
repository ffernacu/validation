package pe.example.app.validation;

import pe.example.app.dto.UserDTO;

public interface Validation {
    UserDTO validateUserDto(UserDTO userDTO);
}
