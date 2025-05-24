package pe.example.app.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pe.example.app.dto.UserDTO;

import java.util.concurrent.CompletableFuture;

@RestController
@Tag(name = "user", description = "operations related to manage user")
public interface UserApi {
    @Operation(
            summary = "void operation",
            description = "operation to valid userDTO",
            responses = {
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                            @Content(mediaType = "application/json")
                    })
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/validate",
            produces = {"application/json"},
            consumes = {"application/json"}
    )
    CompletableFuture<ResponseEntity<Object>> operate(UserDTO userDTO);
}
