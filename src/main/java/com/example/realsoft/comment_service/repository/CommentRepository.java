package com.example.realsoft.comment_service.repository;

import com.example.realsoft.comment_service.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findCommentsByCardId(Long cardId);
    List<Comment> findCommentsByUserId(Long userId);
}
