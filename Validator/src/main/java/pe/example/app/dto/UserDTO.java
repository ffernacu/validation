package pe.example.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull(message= "no debe ser nulo" )
    @NotBlank(message = "no debe ser vacio")
    private String id;
    @NotNull(message= "no debe ser nulo" )
    @NotBlank(message = "no debe ser vacio")
    private String name;
}
