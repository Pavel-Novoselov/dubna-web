package ru.geekbrains.comand.geetterbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.geekbrains.comand.geetterbackend.entities.Comment;
import ru.geekbrains.comand.geetterbackend.entities.Tweet;

import java.util.Collection;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

    Collection<Comment> findByTweetOrderByCreatedDesc(Tweet tweet);
    Collection<Comment> findByTweetIdOrderByCreatedDesc(Long tweetId);
}
