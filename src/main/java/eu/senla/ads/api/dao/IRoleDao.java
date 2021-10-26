package eu.senla.ads.api.dao;

import eu.senla.ads.model.ERole;
import eu.senla.ads.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleDao extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}