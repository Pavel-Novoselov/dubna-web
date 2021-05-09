package ru.geekbrains.comand.geetterbackend.services.interfaces;

import ru.geekbrains.comand.geetterbackend.entities.Tweet;
import ru.geekbrains.comand.geetterbackend.entities.dto.TweetDto;
import ru.geekbrains.comand.geetterbackend.entities.dto.UserDto;

import java.util.List;

public interface TweetService {
    TweetDto saveOrUpdate(TweetDto tweetDto);
    List<TweetDto> findAll();
    TweetDto findById(Long id);
    Tweet doLike(Long id);
    List<TweetDto> findOwnAndBySubcrTweetsByUserId(Long id);
    TweetDto findTopByUserIdByOrderByIdDesc(Long id);
    List<TweetDto> findTop10ByOrderByIdDescUserId(Long id);
    List<TweetDto> findAllByUser(UserDto userDto);
    List<TweetDto> findAllByOrderByCreatedDesc();
    void delete(Long id);
}
