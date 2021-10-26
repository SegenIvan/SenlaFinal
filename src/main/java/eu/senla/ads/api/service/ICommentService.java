package eu.senla.ads.api.service;

import eu.senla.ads.dto.CommentDto;
import eu.senla.ads.dto.CommentPostDto;
import eu.senla.ads.dto.CommentPutDto;

import java.util.List;

public interface ICommentService {
    List<CommentDto> getAll();
    CommentDto findById(Long id);

    Long create(CommentPostDto commentDto) throws Exception;

    void delete(Long id);
    void update(CommentPutDto commentDto) throws Exception;
}
