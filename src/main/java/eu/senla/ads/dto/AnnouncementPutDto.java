package eu.senla.ads.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import eu.senla.ads.model.StateOfAnnouncement;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
public class AnnouncementPutDto {
    @JsonIgnore
    private Long id;
    @NotBlank
    private String text;
    @NotBlank
    private String tag;
    @NotBlank
    private Set<StateOfAnnouncement> states;
}
