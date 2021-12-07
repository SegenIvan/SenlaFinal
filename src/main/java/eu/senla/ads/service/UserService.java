package eu.senla.ads.service;

import eu.senla.ads.api.dao.IUserDao;
import eu.senla.ads.api.service.IUserService;
import eu.senla.ads.dto.UserDto;
import eu.senla.ads.dto.UserPutDto;
import eu.senla.ads.dto.UserRatingDto;
import eu.senla.ads.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDto> getAll() {
        List<User> users = (List<User>) userDao.findAll();
        Type userDto = new TypeToken<List<UserDto>>(){}.getType();
        return modelMapper.map(users,userDto);
    }

    public UserDto findById(Long id){
        User user = userDao.findById(id).orElseThrow(()-> new UsernameNotFoundException("not found user with id:" + id));
        return modelMapper.map(user,UserDto.class);
    }

    public void delete(Long id){
        try {
            userDao.deleteById(id);
        }catch (EntityExistsException e){
            throw new EntityExistsException("Not found by id=" + id);
        }
    }

    public void update(UserPutDto userPutDto) throws Exception {
        User user = userDao.findById(userPutDto.getId()).orElseThrow(() -> new Exception("Not found by id=" + userPutDto.getId()));
        user.setEmail(user.getEmail());
        user.setPhone(userPutDto.getPhone());
        user.getCredentials().setPassword(passwordEncoder.encode(userPutDto.getPassword()));
    }

    public void setRating(UserRatingDto userRatingDto) throws Exception {
        User user = userDao.findById(userRatingDto.getId()).orElseThrow(() -> new Exception("Not found by id=" + userRatingDto.getId()));
        user.setRating(userRatingDto.getRating());
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            User user = userDao.findByCredentialsLogin(login);
            return UserDetailsImpl.build(user);
        }catch (UsernameNotFoundException e){
            throw new UsernameNotFoundException("User Not Found with username: " + login);
        }
    }
}
