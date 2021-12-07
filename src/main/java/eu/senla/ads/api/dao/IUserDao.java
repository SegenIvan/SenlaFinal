package eu.senla.ads.api.dao;

import eu.senla.ads.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends CrudRepository<User,Long> {

    @Query("from User u left join fetch u.credentials c join fetch c.roles r where c.login = :login")
    User findByCredentialsLogin(String login);

    Boolean existsByCredentialsLogin(String login);
    Boolean existsByEmail(String email);
}
