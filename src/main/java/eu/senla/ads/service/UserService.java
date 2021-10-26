package eu.senla.ads.service;

import eu.senla.ads.api.dao.IAnnouncementDao;
import eu.senla.ads.api.dao.IUserDao;
import eu.senla.ads.api.service.IUserService;
import eu.senla.ads.dto.AnnouncementDto;
import eu.senla.ads.dto.UserDto;
import eu.senla.ads.dto.UserPutDto;
import eu.senla.ads.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private ModelMapper modelMapper;

    public List<UserDto> getAll() {
        List<User> users = (List<User>) userDao.findAll();
        Type userDto = new TypeToken<List<UserDto>>(){}.getType();
        return modelMapper.map(users,userDto);
    }

    public UserDto findById(Long id){
        User user = userDao.findById(id).get();
        return modelMapper.map(user,UserDto.class);
    }

    public void delete(Long id){
        userDao.deleteById(id);
    }

    public void update(UserPutDto userPutDto) throws Exception {
        User user = userDao.findById(userPutDto.getId()).orElseThrow(() -> new Exception("Not found by id=" + userPutDto.getId()));
        userDao.save(modelMapper.map(user,User.class));
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            User user = userDao.findByLogin(login);
            return UserDetailsImpl.build(user);
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("User Not Found with username: " + login);
        }
    }
}
