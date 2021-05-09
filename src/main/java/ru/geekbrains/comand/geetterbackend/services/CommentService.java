package ru.geekbrains.comand.geetterbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.comand.geetterbackend.entities.Comment;
import ru.geekbrains.comand.geetterbackend.entities.dto.CommentDto;
import ru.geekbrains.comand.geetterbackend.repositories.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public CommentDto save(final Comment comment) {
        final Comment savedComment = commentRepository.save(comment);
        return new CommentDto(
                savedComment.getUser().getId(),
                savedComment.getTweet().getId(),
                savedComment.getCreated(),
                savedComment.getText()
        );
    }

}
