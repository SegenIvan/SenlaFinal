package eu.senla.ads.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class CommentPostDto {
    @NotNull
    @Positive
    private Long authorId;
    @NotBlank
    private String message;
    @NotNull
    @Positive
    private Long announcementId;
}