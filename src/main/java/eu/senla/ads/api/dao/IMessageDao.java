package eu.senla.ads.api.dao;

import eu.senla.ads.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageDao extends CrudRepository<Message, Long> {
    @Query("from Message m " +
            "where (m.recipient.id = :toId and m.sender.id = :fromId) or " +
            "(m.sender.id = :toId and m.recipient.id = :fromId)")
    List<Message> getMessageFromTo(Long fromId, Long toId);
}
