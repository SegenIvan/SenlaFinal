package eu.senla.ads.dto;

import eu.senla.ads.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Set<Role> roles;
    private boolean isActive;
}
