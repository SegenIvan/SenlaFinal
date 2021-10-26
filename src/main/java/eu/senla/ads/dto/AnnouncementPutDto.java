package eu.senla.ads.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AnnouncementPutDto {
    @JsonIgnore
    private Long id;
    @NotBlank
    private String text;
    @NotBlank
    private String tag;
}
