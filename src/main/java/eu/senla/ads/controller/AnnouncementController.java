package eu.senla.ads.controller;

import eu.senla.ads.api.service.IAnnouncementService;
import eu.senla.ads.dto.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/announcements")
@Validated
public class AnnouncementController {
    @Autowired
    private IAnnouncementService announcementService;
    private static final Logger logger = LogManager.getLogger(AnnouncementController.class);

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDto> getById(@PathVariable Long id){
        logger.info("received request: /announcement" + id);
        return ResponseEntity.ok(announcementService.findById(id));
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<AnnouncementDto>> getAll(){
        return ResponseEntity.ok(announcementService.getAll());
    }

    @GetMapping("/allActive")
    public ResponseEntity<List<AnnouncementGetDto>> getAllActive(){
        return ResponseEntity.ok(announcementService.getAllActive());
    }

    @GetMapping("{id}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsByAnnouncement(@PathVariable Long id){
        return ResponseEntity.ok(announcementService.getCommentsByAnnouncement(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        announcementService.delete(id);
        logger.info("received request: /delete/" + id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/offAnnouncement/{id}")
    public ResponseEntity<Void> offAnnouncement(@PathVariable Long id){
        announcementService.offAnnouncement(announcementService.findById(id));
        logger.info("received request: /offAnnouncement/" + id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @RequestBody @Valid AnnouncementPutDto announcementPutDto) throws Exception {
        logger.info("received request: /update/" + id);
        announcementPutDto.setId(id);
        announcementService.update(announcementPutDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<Void> pay(@PathVariable Long id) {
        logger.info("received request: /update/" + id);
        announcementService.pay(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addAds")
    public ResponseEntity<IdDto> addAnnouncement(@RequestBody @Valid AnnouncementPostDto announcementPostDto) throws Exception {
       Long id = announcementService.create(announcementPostDto);
        logger.info("received request: /addAnnouncement " + announcementPostDto);
        return ResponseEntity.ok(new IdDto(id));
    }

    @GetMapping("/sortByDateOfCreate")
    public ResponseEntity<List<AnnouncementGetDto>> sortByDateOfCreate(){
        return ResponseEntity.ok(announcementService.sortByDateOfCreate());
    }
    @GetMapping("/getAnnouncementsByAuthor/{id}")
    public ResponseEntity<List<AnnouncementDto>> getAnnouncementsByAuthor(@PathVariable Long id){
        return ResponseEntity.ok(announcementService.getAnnouncementsByAuthor(id));
    }
    @GetMapping("/getAnnouncementsByTag/{tag}")
    public ResponseEntity<List<AnnouncementGetDto>> getAnnouncementsByTag(@PathVariable String tag){
        return ResponseEntity.ok(announcementService.findByTag(tag));
    }

    @GetMapping("/sortByAuthorRating")
    public ResponseEntity<List<AnnouncementDtoWithRating>> sortByAuthorRating(){
        return ResponseEntity.ok(announcementService.sortByAuthorRating());
    }
}
