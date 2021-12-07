package eu.senla.ads.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AnnouncementGetDto {
    private Long id;
    private UserGetDto author;
    private LocalDate dateOfCreate;
    private String text;
    private String tag;
}
