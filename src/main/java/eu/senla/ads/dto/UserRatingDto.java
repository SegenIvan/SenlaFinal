package eu.senla.ads.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class UserRatingDto {
    @NotBlank
    @Positive
    private Long id;
    @NotBlank
    private Integer rating;
}
