package eu.senla.ads.config.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@Validated
public class SignupRequest {
    @NotBlank
    private String login;
    @NotBlank
    private String email;
    private Set<String> roles;
    @NotBlank
    private String phone;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}
