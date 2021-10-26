package eu.senla.ads.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class AnnouncementPostDto {
    @NotNull
    @Positive
    private Long id;
    @NotBlank
    private String text;
    @NotBlank
    private String tag;
    @NotNull
    @Positive
    private Long authorId;
}
