package eu.senla.ads.controller;

import eu.senla.ads.api.service.IAnnouncementService;
import eu.senla.ads.api.service.ICommentService;
import eu.senla.ads.api.service.IUserService;
import eu.senla.ads.dto.CommentDto;
import eu.senla.ads.dto.CommentPostDto;
import eu.senla.ads.dto.CommentPutDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Validated
public class CommentController {
    private final ICommentService commentService;
    private static final Logger logger = LogManager.getLogger(CommentController.class);

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getById(@PathVariable Long id){
        logger.info("received request: /comment" + id);
        return ResponseEntity.ok(commentService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CommentDto>> getAll(){
        return ResponseEntity.ok(commentService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        logger.info("received request: /delete/" + id);
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid CommentPutDto commentPutDto) throws Exception {
        commentPutDto.setId(id);
        commentService.update(commentPutDto);
        logger.info("received request: /update/" + id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Void> addComment(@RequestBody @Valid CommentPostDto commentPostDto) throws Exception {
        commentService.create(commentPostDto);
        logger.info("received request: /addComment " + commentPostDto);
        return ResponseEntity.noContent().build();
    }
}
