package ru.geekbrains.comand.geetterbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.geekbrains.comand.geetterbackend.entities.Tweet;
import ru.geekbrains.comand.geetterbackend.entities.User;

import java.util.List;
import java.util.Optional;

public interface TweetRepository extends JpaRepository<Tweet, Long>, JpaSpecificationExecutor<Tweet> {

    Optional<Tweet> findByUser(User user);
    Optional<Tweet> findByUserId(Long userId);
    Optional<Tweet> findByOrderByCreatedDesc();
    Optional<Tweet> findTopByUserIdOrderByCreatedDesc(Long id);
    Optional<List<Tweet>> findAllByUserIdOrderByCreatedDesc(Long id);
    Optional<List<Tweet>> findFirst10ByUserIdOrderByCreatedDesc(Long id);
    List<Tweet> findAllByOrderByCreatedDesc();
    Optional<List<Tweet>> findAllByUser(User user);
}
