package com.example.realsoft.comment_service.service;

import com.example.realsoft.comment_service.exception.CommentNotFound;
import com.example.realsoft.comment_service.model.CommentDto;

public interface CommentService {
    CommentDto getComment(Long commentId) throws CommentNotFound;
    CommentDto createComment(CommentDto commentDto);
    CommentDto editComment(Long commentId, CommentDto commentDto) throws CommentNotFound;
    void deleteComment(Long commentId) throws CommentNotFound;
}
