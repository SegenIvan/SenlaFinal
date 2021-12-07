package eu.senla.ads.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
public class CommentDto {
    @NotBlank
    @Positive
    private Long id;
    @NotBlank
    private UserGetDto author;
    @NotBlank
    private LocalDate dateOfCreate;
    @NotBlank
    private String message;
}
