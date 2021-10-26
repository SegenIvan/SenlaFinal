package eu.senla.ads.controller;

import eu.senla.ads.api.service.IAnnouncementService;
import eu.senla.ads.dto.AnnouncementDto;
import eu.senla.ads.dto.AnnouncementPostDto;
import eu.senla.ads.dto.AnnouncementPutDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/announcements")
@RequiredArgsConstructor
@Validated
public class AnnouncementController {
    private final IAnnouncementService announcementService;
    private static final Logger logger = LogManager.getLogger(AnnouncementController.class);

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementDto> getById(@PathVariable Long id){
        logger.info("received request: /announcement" + id);
        return ResponseEntity.ok(announcementService.findById(id));
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @GetMapping("/all")
    public ResponseEntity<List<AnnouncementDto>> getAll(){
        return ResponseEntity.ok(announcementService.getAll());
    }

    @GetMapping("/allActive")
    public ResponseEntity<List<AnnouncementDto>> getAllActive(){
        return ResponseEntity.ok(announcementService.getAllActive());
    }

    @PreAuthorize("hasRole('MODERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        logger.info("received request: /delete/" + id);
        announcementService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/offAnnouncement/{id}")
    public ResponseEntity<Void> offAnnouncement(@PathVariable Long id){
        logger.info("received request: /offAnnouncement/" + id);
        announcementService.offAnnouncement(announcementService.findById(id));
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

    @PostMapping("/addAds")
    public ResponseEntity<Void> addAnnouncement(@RequestBody @Valid AnnouncementPostDto announcementPostDto) throws Exception {
        announcementService.create(announcementPostDto);
        logger.info("received request: /addAnnouncement " + announcementPostDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/sortByDateOfCreate")
    public ResponseEntity<List<AnnouncementDto>> sortByDateOfCreate(){
        return ResponseEntity.ok(announcementService.sortByDateOfCreate());
    }
    @GetMapping("/getAnnouncementsByAuthor/{id}")
    public ResponseEntity<List<AnnouncementDto>> getAnnouncementsByAuthor(@PathVariable Long id){
        return ResponseEntity.ok(announcementService.getAnnouncementsByAuthor(id));
    }
}
