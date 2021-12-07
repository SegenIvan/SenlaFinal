package eu.senla.ads.api.service;

import eu.senla.ads.dto.*;

import java.util.List;

public interface IAnnouncementService {
    List<AnnouncementDto> getAll();
    AnnouncementDto findById(Long id);
    Long create(AnnouncementPostDto announcementPostDto) throws Exception;
    void delete(Long id);
    void update(AnnouncementPutDto announcementDto) throws Exception;
    List<AnnouncementGetDto> sortByDateOfCreate();
    List<AnnouncementDto> getAnnouncementsByAuthor(Long id);
    List<AnnouncementGetDto> findByTag(String tag);
    void offAnnouncement(AnnouncementDto announcementDto);
    List<AnnouncementGetDto> getAllActive();
    List<AnnouncementDtoWithRating> sortByAuthorRating();
    List<CommentDto> getCommentsByAnnouncement(Long id);
    void pay(Long id);
}
