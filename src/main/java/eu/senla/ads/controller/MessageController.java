package eu.senla.ads.controller;

import eu.senla.ads.api.service.IMessageService;
import eu.senla.ads.dto.CommentDto;
import eu.senla.ads.dto.IdDto;
import eu.senla.ads.dto.MessageGetDto;
import eu.senla.ads.dto.MessagePostDto;
import eu.senla.ads.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/messages")
@Validated
public class MessageController {
    @Autowired
    private IMessageService messageService;
    @PostMapping
    public ResponseEntity<IdDto> addMessage(@RequestBody @Valid MessagePostDto messagePostDto) {
      Long id = messageService.addMessage(messagePostDto);
        return ResponseEntity.ok(new IdDto(id));
    }

    @GetMapping("/from/{fromId}/to/{toId}")
    public ResponseEntity<List<MessageGetDto>> messages(@PathVariable("fromId") Long fromId, @PathVariable("toId") Long toId){
        return ResponseEntity.ok(messageService.getMessageFromTo(fromId, toId));
    }

}
