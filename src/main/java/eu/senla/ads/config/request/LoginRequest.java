package eu.senla.ads.config.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Validated
public class LoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
