package ru.geekbrains.comand.geetterbackend.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.comand.geetterbackend.entities.Comment;
import ru.geekbrains.comand.geetterbackend.entities.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TweetDto {

    private Long id;
    private LocalDateTime created;
    private User user;
    private List<Comment> comments;
    private String text;
    private Boolean isPublished;
    private Long likes;

}
