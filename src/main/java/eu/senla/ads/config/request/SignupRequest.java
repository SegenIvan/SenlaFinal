package eu.senla.ads.config.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {
    @NotBlank
    private String login;
    @NotBlank
    private String email;
    private Set<String> roles;//todo validation
    @NotBlank
    private String phone;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
}
