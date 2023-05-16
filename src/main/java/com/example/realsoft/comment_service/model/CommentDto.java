package com.example.realsoft.comment_service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long userId;
    private Long cardId;
    private String content;
}
