package eu.senla.ads.service;

import eu.senla.ads.api.dao.IAnnouncementDao;
import eu.senla.ads.api.dao.IAnnouncementStateDao;
import eu.senla.ads.api.dao.IUserDao;
import eu.senla.ads.api.service.IAnnouncementService;
import eu.senla.ads.dto.*;
import eu.senla.ads.model.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class AnnouncementService implements IAnnouncementService {
    @Autowired
    private IAnnouncementDao announcementDao;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IAnnouncementStateDao announcementStateDao;

    public List<AnnouncementDto> getAll() {
        List<Announcement> announcements = (List<Announcement>) announcementDao.findAll();
        Type announcementDto = new TypeToken<List<AnnouncementDto>>(){}.getType();
        return modelMapper.map(announcements,announcementDto);
    }

    public List<AnnouncementGetDto> getAllActive() {
        StateOfAnnouncement stateOfAnnouncementNotActive = announcementStateDao.findByName(EStateOfAnnouncement.ACTIVE).
                orElseThrow(() -> new RuntimeException("Error, State Not active is not found"));
        List<Announcement> announcements = announcementDao.
                findAnnouncementsByStatesContaining(stateOfAnnouncementNotActive,Sort.by("isPaid").descending());
        Type announcementGetDto = new TypeToken<List<AnnouncementGetDto>>(){}.getType();
        return modelMapper.map(announcements,announcementGetDto);
    }

    public List<CommentDto> getCommentsByAnnouncement(Long id){
        Announcement announcement = announcementDao.findById(id).
                orElseThrow(() -> new EntityExistsException("not found announcement with id:" + id));
        List<Comment> comments = announcement.getComments();
        Type commentDto = new TypeToken<List<CommentDto>>(){}.getType();
        return modelMapper.map(comments,commentDto);
    }

    public AnnouncementDto findById(Long id){
        Announcement announcement = announcementDao.findById(id).orElseThrow(()-> new EntityExistsException("not found announcement with id:" + id));
        return modelMapper.map(announcement,AnnouncementDto.class);
    }

    public Long create(AnnouncementPostDto announcementPostDto) {
        User user = userDao.findById(announcementPostDto.getAuthorId()).orElseThrow(() -> new EntityExistsException("Not found by id=" + announcementPostDto.getAuthorId()));
        Announcement announcement = modelMapper.map(announcementPostDto,Announcement.class);
        announcement.setAuthor(user);
        announcement.setDateOfCreate(LocalDate.now());
        StateOfAnnouncement stateOfAnnouncementActive = announcementStateDao.findByName(EStateOfAnnouncement.ACTIVE).
                orElseThrow(() -> new EntityExistsException("Error, State ACTIVE is not found"));

        announcement.setIsPaid(false);
        announcement.getStates().add(stateOfAnnouncementActive);
        announcementDao.save(announcement);
        return announcement.getId();
    }

    public void delete(Long id){
        announcementDao.deleteById(id);
    }

    public void update(AnnouncementPutDto announcementDto) {
        Announcement announcement = announcementDao.findById(announcementDto.getId()).orElseThrow(() -> new EntityNotFoundException("Not found by id=" + announcementDto.getId()));
        announcement.setText(announcementDto.getText());
        announcement.setTag(announcementDto.getTag());
        announcementDao.save(modelMapper.map(announcementDto,Announcement.class));
    }

    public void pay(Long id) {
        Announcement announcement = announcementDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found by id=" + id));
        announcement.setDateOfPayment(LocalDate.now());
        announcement.setIsPaid(true);
    }

    public void offAnnouncement(AnnouncementDto announcementDto){
        StateOfAnnouncement stateOfAnnouncementNotActive = announcementStateDao.findByName(EStateOfAnnouncement.NOT_ACTIVE).
                orElseThrow(() -> new NoSuchElementException("Error, State Not active is not found"));
        announcementDto.getStates().add(stateOfAnnouncementNotActive);
    }

    public List<AnnouncementGetDto> sortByDateOfCreate() {
        List<Announcement> announcements = announcementDao.
                getAnnouncementsAndSortByDateOfCreate(Sort.by("dateOfCreate").descending());
        Type announcementDto = new TypeToken<List<AnnouncementGetDto>>(){}.getType();
        return modelMapper.map(announcements,announcementDto);
    }

    public List<AnnouncementDto> getAnnouncementsByAuthor(Long id) {
        List<Announcement> announcementsList = announcementDao.findAnnouncementByAuthorId(id);
        Type announcementDto = new TypeToken<List<AnnouncementDto>>(){}.getType();
        return modelMapper.map(announcementsList,announcementDto);
    }

    public List<AnnouncementGetDto> findByTag(String tag){
        Type announcementDto = new TypeToken<List<AnnouncementGetDto>>(){}.getType();
        return modelMapper.map(announcementDao.findByTag(tag),announcementDto);
    }

    public List<AnnouncementDtoWithRating> sortByAuthorRating(){
        return announcementDao.
                getAnnouncementsWithAuthorRatingAndSort(Sort.by("u.rating").descending());
    }
}
