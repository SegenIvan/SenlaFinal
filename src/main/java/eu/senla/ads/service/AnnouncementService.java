package eu.senla.ads.service;

import eu.senla.ads.api.dao.IAnnouncementDao;
import eu.senla.ads.api.dao.IUserDao;
import eu.senla.ads.api.service.IAnnouncementService;
import eu.senla.ads.dto.AnnouncementDto;
import eu.senla.ads.dto.AnnouncementPostDto;
import eu.senla.ads.dto.AnnouncementPutDto;
import eu.senla.ads.model.Announcement;
import eu.senla.ads.model.EStateOfAnnouncement;
import eu.senla.ads.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
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

    public List<AnnouncementDto> getAll() {
        Collection<Announcement> announcements = (Collection<Announcement>) announcementDao.findAll();
        Type announcementDto = new TypeToken<List<AnnouncementDto>>(){}.getType();
        return modelMapper.map(announcements,announcementDto);
    }

    public List<AnnouncementDto> getAllActive() {
        List<Announcement> announcements = (List<Announcement>) announcementDao.findAll();
        announcements.removeIf(announcement -> announcement.getStates().contains(EStateOfAnnouncement.NOT_ACTIVE));
        Type announcementDto = new TypeToken<List<AnnouncementDto>>(){}.getType();
        return modelMapper.map(announcements,announcementDto);
    }

    public AnnouncementDto findById(Long id){
        Announcement announcement = announcementDao.findById(id).get();
        return modelMapper.map(announcement,AnnouncementDto.class);
    }

    public Long create(AnnouncementPostDto announcementPostDto) throws Exception {
        User user = userDao.findById(announcementPostDto.getAuthorId()).orElseThrow(() -> new Exception("Not found by id=" + announcementPostDto.getAuthorId()));
        Announcement announcement = modelMapper.map(announcementPostDto,Announcement.class);
        announcement.setAuthor(user);
        announcementDao.save(announcement);
        return announcement.getId();
    }

    public void delete(Long id){
        announcementDao.deleteById(id);
    }

    public void update(AnnouncementPutDto announcementDto) throws Exception {
        Announcement announcement = announcementDao.findById(announcementDto.getId()).orElseThrow(() -> new Exception("Not found by id=" + announcementDto.getId()));
        announcement.setText(announcementDto.getText());
        announcement.setTag(announcementDto.getTag());
        announcementDao.save(modelMapper.map(announcementDto,Announcement.class));
    }

    public void offAnnouncement(AnnouncementDto announcementDto){
        announcementDto.setActive(false);
    }

    public List<AnnouncementDto> sortByDateOfCreate() {
        List<Announcement> announcements = (List<Announcement>) announcementDao.findAll();
        announcements.sort(Comparator.comparing(Announcement::getDateOfCreate));
        Type announcementDto = new TypeToken<List<AnnouncementDto>>(){}.getType();
        return modelMapper.map(announcements,announcementDto);
    }

    public List<AnnouncementDto> getAnnouncementsByAuthor(Long id) {
        List<Announcement> announcementsList = new ArrayList<>();
        for (Announcement announcement: announcementDao.findAll()){
            if (Objects.equals(announcement.getAuthor().getId(), id)){
                announcementsList.add(announcement);
            }
        }
        Type announcementDto = new TypeToken<List<AnnouncementDto>>(){}.getType();
        return modelMapper.map(announcementsList,announcementDto);
    }

    public List<AnnouncementDto> announcementsByAuthorRating() {
        List<Announcement> announcements = (List<Announcement>) announcementDao.findAll();
       //todo announcements.sort(Comparator.comparing(Announcement::getAuthor));
        Type announcementDto = new TypeToken<List<AnnouncementDto>>(){}.getType();
        return modelMapper.map(announcements,announcementDto);
    }

    public List<AnnouncementDto> findByTag(String tag){
        Type announcementDto = new TypeToken<List<AnnouncementDto>>(){}.getType();
        return modelMapper.map(announcementDao.findByTag(tag),announcementDto);
    }
}
