package eu.senla.ads.service;

import eu.senla.ads.api.dao.IAnnouncementDao;
import eu.senla.ads.api.dao.ICommentDao;
import eu.senla.ads.api.dao.IUserDao;
import eu.senla.ads.api.service.ICommentService;
import eu.senla.ads.dto.CommentDto;
import eu.senla.ads.dto.CommentPostDto;
import eu.senla.ads.dto.CommentPutDto;
import eu.senla.ads.model.Announcement;
import eu.senla.ads.model.Comment;
import eu.senla.ads.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
public class CommentService implements ICommentService {
    @Autowired
    private ICommentDao commentDao;
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IAnnouncementDao announcementDao;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CommentDto> getAll() {
        List<Comment> users = (List<Comment>) commentDao.findAll();
        Type commentDto = new TypeToken<List<CommentDto>>(){}.getType();
        return modelMapper.map(users,commentDto);
    }

    @Override
    public CommentDto findById(Long id) {
        Comment comment = commentDao.findById(id).get();
        return modelMapper.map(comment,CommentDto.class);
    }

    @Override
    public Long create(CommentPostDto commentDto) throws Exception {
        User user = userDao.findById(commentDto.getAuthorId()).orElseThrow(()-> new Exception("Not found by id=" + commentDto.getAuthorId()));
        Announcement announcement = announcementDao.findById(commentDto.getAnnouncementId()).orElseThrow(()-> new Exception("Not found by id=" + commentDto.getAnnouncementId()));
        Comment comment = modelMapper.map(commentDto,Comment.class);
        comment.setAnnouncement(announcement);
        comment.setAuthor(user);
        commentDao.save(comment);
        return comment.getId();
    }

    @Override
    public void delete(Long id) {
        commentDao.deleteById(id);
    }

    @Override
    public void update(CommentPutDto commentDto) throws Exception {
        Comment comment = commentDao.findById(commentDto.getId()).orElseThrow(() -> new Exception("Not found by id=" + commentDto.getId()));
        comment.setMessage(commentDto.getMessage());
    }
}
