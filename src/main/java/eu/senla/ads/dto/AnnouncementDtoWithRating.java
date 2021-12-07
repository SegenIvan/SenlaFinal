package eu.senla.ads.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
public class AnnouncementDtoWithRating {
    @NotBlank
    private LocalDate dateOfCreate;
    @NotBlank
    private String text;
    @NotBlank
    private String tag;
    @NotBlank
    @Positive
    private Integer rating;

    public AnnouncementDtoWithRating(LocalDate dateOfCreate, String text, String tag, Integer rating) {
        this.dateOfCreate = dateOfCreate;
        this.text = text;
        this.tag = tag;
        this.rating = rating;
    }
}
