package eu.senla.ads.api.dao;

import eu.senla.ads.model.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAnnouncementDao extends CrudRepository<Announcement,Long> {
    List<Announcement> findByTag(String tag);
}
