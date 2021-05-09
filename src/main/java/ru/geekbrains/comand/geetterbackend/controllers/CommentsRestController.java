package ru.geekbrains.comand.geetterbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.comand.geetterbackend.entities.Comment;
import ru.geekbrains.comand.geetterbackend.entities.dto.CommentDto;
import ru.geekbrains.comand.geetterbackend.mappers.TweetMapper;
import ru.geekbrains.comand.geetterbackend.mappers.UserMapper;
import ru.geekbrains.comand.geetterbackend.services.CommentService;
import ru.geekbrains.comand.geetterbackend.services.interfaces.TweetService;
import ru.geekbrains.comand.geetterbackend.services.interfaces.UserService;

import java.time.LocalDateTime;

@RestController
@RequestMapping(path = "/api/comments")
public class CommentsRestController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private TweetService tweetService;


    @PostMapping
    public CommentDto createComment(@RequestBody final CommentDto newComment) {
        if (newComment.getText() == null) {
            throw new IllegalArgumentException("No text found in comment");
            // только создание никакого редактирования
        }
        final Comment comment = new Comment();
        comment.setCreated(LocalDateTime.now());
        comment.setText(newComment.getText());
        comment.setUser(UserMapper.MAPPER.toUser(userService.findById(newComment.getUserId())));
        comment.setTweet(TweetMapper.MAPPER.toTweet(tweetService.findById(newComment.getTweetId())));

        return commentService.save(comment);
    }
}
