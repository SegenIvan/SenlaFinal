package eu.senla.ads.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private UserDto author;
    private LocalDate dateOfCreate;
    private String message;
}
