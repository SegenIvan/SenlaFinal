package eu.senla.ads.api.service;

import eu.senla.ads.dto.MessageGetDto;
import eu.senla.ads.dto.MessagePostDto;

import java.util.List;

public interface IMessageService {
   Long addMessage(MessagePostDto messagePostDto);

   List<MessageGetDto> getMessageFromTo(Long fromId, Long toId);
}
