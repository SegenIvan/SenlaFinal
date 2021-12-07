package eu.senla.ads.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthorNameDto {
    @NotBlank
    private String name;
}
