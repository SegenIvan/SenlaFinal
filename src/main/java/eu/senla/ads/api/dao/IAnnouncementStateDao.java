package eu.senla.ads.api.dao;

import eu.senla.ads.model.EStateOfAnnouncement;
import eu.senla.ads.model.StateOfAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IAnnouncementStateDao extends JpaRepository<StateOfAnnouncement, Long> {
    Optional<StateOfAnnouncement> findByName(EStateOfAnnouncement name);
}
