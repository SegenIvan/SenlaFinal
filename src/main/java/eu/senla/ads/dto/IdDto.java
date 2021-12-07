package eu.senla.ads.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class IdDto {
    @NotBlank
    @Positive
    private Long id;
}
