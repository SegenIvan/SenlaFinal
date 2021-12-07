package eu.senla.ads.api.dao;

import eu.senla.ads.dto.AnnouncementDtoWithRating;
import eu.senla.ads.model.Announcement;
import eu.senla.ads.model.EStateOfAnnouncement;
import eu.senla.ads.model.StateOfAnnouncement;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnnouncementDao extends CrudRepository<Announcement,Long> {
    List<Announcement> findByTag(String tag);

    List<Announcement> findAnnouncementByAuthorId(Long id);

    @Query("SELECT new eu.senla.ads.dto.AnnouncementDtoWithRating(a.dateOfCreate, a.text, a.tag, u.rating)" +
            " from Announcement a join a.author u")
    List<AnnouncementDtoWithRating> getAnnouncementsWithAuthorRatingAndSort(Sort sort);

    @Query("from Announcement a")
    List<Announcement> getAnnouncementsAndSortByDateOfCreate(Sort sort);

    List<Announcement> findAnnouncementsByStatesContaining(StateOfAnnouncement state, Sort sort);
}
