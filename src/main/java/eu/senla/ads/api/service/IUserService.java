package eu.senla.ads.api.service;

import eu.senla.ads.dto.UserDto;
import eu.senla.ads.dto.UserPutDto;
import eu.senla.ads.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {
    List<UserDto> getAll();
    UserDto findById(Long id);
    void delete(Long id);
    void update(UserPutDto userPutDto) throws Exception;
}
