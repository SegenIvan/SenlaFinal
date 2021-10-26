package eu.senla.ads.api.dao;

import eu.senla.ads.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentDao extends CrudRepository<Comment,Long> {
}
