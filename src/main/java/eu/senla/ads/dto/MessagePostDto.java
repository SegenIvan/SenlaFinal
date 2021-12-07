package eu.senla.ads.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
public class MessagePostDto {
    @NotNull
    private LocalDate dateSent;
    @NotBlank
    private String text;
    @NotNull
    @Positive
    private Long recipientId;
    @NotNull
    @Positive
    private Long senderId;
}
