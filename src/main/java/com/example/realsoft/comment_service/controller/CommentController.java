package com.example.realsoft.comment_service.controller;

import com.example.realsoft.comment_service.exception.CommentNotFound;
import com.example.realsoft.comment_service.model.CommentDto;
import com.example.realsoft.comment_service.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("realsoft/trello/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable(name = "id") Long commentId) throws CommentNotFound {
        return ResponseEntity.ok(commentService.getComment(commentId));
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(commentDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> editComment(@PathVariable(name = "id") Long commentId, @RequestBody CommentDto commentDto) throws CommentNotFound {
        return ResponseEntity.ok(commentService.editComment(commentId, commentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable(name = "id") Long commentId) throws CommentNotFound {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully!");
    }
}
