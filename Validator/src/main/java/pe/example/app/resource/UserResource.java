package pe.example.app.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import pe.example.app.api.UserApi;
import pe.example.app.dto.UserDTO;
import pe.example.app.validation.Validation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class UserResource implements UserApi {

    private final Validation validation;

    @Override
    public CompletableFuture<ResponseEntity<Object>> operate(@RequestBody UserDTO userDTO){
        return CompletableFuture
                .supplyAsync(
                    () -> {
                        validation.validateUserDto(userDTO);
                        return ResponseEntity.noContent().build();
                    })
                .exceptionally(this::ExceptionResponseEntity);
    }

    private ResponseEntity<Object> ExceptionResponseEntity(Throwable ex) {
        // Log the exception
        System.err.println("Exception occurred: " + ex.getMessage());

        // Prepare error details as JSON directly
        Map<String, String> errorDetails = Map.of(
                "error", "Validation failed: " + ex.getMessage(),
                "exceptionType", ex.getClass().getSimpleName()
        );

        // Return a custom response entity with an error message
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(convertToJson(errorDetails));
    }

    // Example method to convert Map to JSON (assuming Jackson ObjectMapper usage)
    private String convertToJson(Map<String, String> map) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}"; // Fallback to empty JSON object
        }
    }
}
