package eu.senla.ads.controller;

import eu.senla.ads.api.service.IUserService;
import eu.senla.ads.dto.CommentPutDto;
import eu.senla.ads.dto.UserDto;
import eu.senla.ads.dto.UserPutDto;
import eu.senla.ads.model.Role;
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
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final IUserService userService;
    private static final Logger logger = LogManager.getLogger(UserController.class);

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        logger.info("received request: /user" + id);
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteById(@PathVariable Long id) {
        logger.info("received request: /delete/" + id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id,
                                          @RequestBody @Valid UserPutDto userPutDto) throws Exception {
        userPutDto.setId(id);
        userService.update(userPutDto);
        logger.info("received request: /update/" + id);
        return ResponseEntity.noContent().build();
    }
}
