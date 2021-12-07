package eu.senla.ads.dto;

import eu.senla.ads.model.StateOfAnnouncement;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class AnnouncementDto {
    @NotBlank
    @Positive
    private Long id;
    @NotBlank
    private UserDto author;
    @NotBlank
    private LocalDate dateOfCreate;
    @NotBlank
    private Set<StateOfAnnouncement> states;
    @NotBlank
    private String text;
    @NotBlank
    private String tag;
}
