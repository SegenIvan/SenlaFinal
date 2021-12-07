package eu.senla.ads.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class UserGetDto {
    private String name;
    private String email;
    private int rating;
}
