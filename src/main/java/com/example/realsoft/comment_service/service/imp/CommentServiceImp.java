package com.example.realsoft.comment_service.service.imp;

import com.example.realsoft.comment_service.entity.Comment;
import com.example.realsoft.comment_service.exception.CommentNotFound;
import com.example.realsoft.comment_service.model.CommentDto;
import com.example.realsoft.comment_service.repository.CommentRepository;
import com.example.realsoft.comment_service.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentDto getComment(Long commentId) throws CommentNotFound {
        return modelMapper.map(findComment(commentId), CommentDto.class);
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setCardId(commentDto.getCardId());
        comment.setUserId(commentDto.getUserId());
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public CommentDto editComment(Long commentId, CommentDto commentDto) throws CommentNotFound {
        Comment comment = findComment(commentId);
        comment.setContent(commentDto.getContent());
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public void deleteComment(Long commentId) throws CommentNotFound {
        commentRepository.delete(findComment(commentId));
    }

    @Override
    public List<CommentDto> findCommentsByUser(Long userId) {
        return commentRepository.findCommentsByUserId(userId).stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> findCommentsByCard(Long cardId) {
        return commentRepository.findCommentsByCardId(cardId).stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    private Comment findComment(Long commentId) throws CommentNotFound {
        return commentRepository.findById(commentId).orElseThrow(() ->
                new CommentNotFound("Id", commentId));
    }
}
