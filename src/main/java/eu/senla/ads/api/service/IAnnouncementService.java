package eu.senla.ads.api.service;

import eu.senla.ads.dto.AnnouncementDto;
import eu.senla.ads.dto.AnnouncementPostDto;
import eu.senla.ads.dto.AnnouncementPutDto;

import java.util.List;

public interface IAnnouncementService {
    List<AnnouncementDto> getAll();
    AnnouncementDto findById(Long id);
    Long create(AnnouncementPostDto announcementPostDto) throws Exception;
    void delete(Long id);
    void update(AnnouncementPutDto announcementDto) throws Exception;
    List<AnnouncementDto> sortByDateOfCreate();
    List<AnnouncementDto> getAnnouncementsByAuthor(Long id);
    List<AnnouncementDto> findByTag(String tag);
    void offAnnouncement(AnnouncementDto announcementDto);
    List<AnnouncementDto> getAllActive();
}
