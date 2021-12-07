package eu.senla.ads.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
public class MessageGetDto {
    @NotBlank
    private LocalDate dateSent;
    @NotBlank
    private String text;
    @NotBlank
    private UserIdDto recipient;
    @NotBlank
    private UserIdDto sender;
}
