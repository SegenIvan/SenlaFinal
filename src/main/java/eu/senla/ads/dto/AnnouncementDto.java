package eu.senla.ads.dto;

import eu.senla.ads.model.StateOfAnnouncement;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnnouncementDto {
    private Long id;
    private UserDto author;
    private LocalDate dateOfCreate;
    private StateOfAnnouncement stateOfAnnouncement;
    private LocalDate dateOfPayment;
    private String text;
    private String tag;
    private boolean isActive;
}
