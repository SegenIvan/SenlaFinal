package eu.senla.ads.service;

import eu.senla.ads.api.dao.IMessageDao;
import eu.senla.ads.api.dao.IUserDao;
import eu.senla.ads.api.service.IMessageService;
import eu.senla.ads.dto.AnnouncementDto;
import eu.senla.ads.dto.MessageGetDto;
import eu.senla.ads.dto.MessagePostDto;
import eu.senla.ads.model.Message;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class MessageService implements IMessageService {
   @Autowired
    private IUserDao userDao;
   @Autowired
   private IMessageDao messageDao;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Long addMessage(MessagePostDto messagePostDto) {
        Message message = new Message();
        message.setIsRead(false);
        message.setRecipient(userDao.findById(messagePostDto.getRecipientId()).orElseThrow(() -> new EntityNotFoundException("not found entity with id:" + messagePostDto.getRecipientId())));
        message.setSender(userDao.findById(messagePostDto.getSenderId()).orElseThrow(() -> new EntityNotFoundException("not found entity with id:" + messagePostDto.getSenderId())));
        message.setText(messagePostDto.getText());
        message.setDateSent(messagePostDto.getDateSent());
        messageDao.save(message);
        return message.getId();
    }

    @Override
    public List<MessageGetDto> getMessageFromTo(Long fromId, Long toId) {
        Type messageGetDto = new TypeToken<List<MessageGetDto>>(){}.getType();
        return modelMapper.map(messageDao.getMessageFromTo(fromId, toId),messageGetDto);
    }
}
