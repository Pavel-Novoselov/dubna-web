package ru.geekbrains.comand.geetterbackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.comand.geetterbackend.entities.dto.TweetDto;
import ru.geekbrains.comand.geetterbackend.entities.dto.UserDto;
import ru.geekbrains.comand.geetterbackend.entities.Tweet;
import ru.geekbrains.comand.geetterbackend.exceptions.NotFoundForTheRequestedParamException;
import ru.geekbrains.comand.geetterbackend.mappers.TweetMapper;
import ru.geekbrains.comand.geetterbackend.mappers.UserMapper;
import ru.geekbrains.comand.geetterbackend.repositories.TweetRepository;
import ru.geekbrains.comand.geetterbackend.services.interfaces.TweetService;
import ru.geekbrains.comand.geetterbackend.services.interfaces.UserService;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;
    private final UserService userService;

    private List<TweetDto> findTop10Tweets(List<TweetDto> tweets){
        if (tweets != null && tweets.size() >= 10){
            tweets.subList(10, tweets.size());
            return tweets;
        }
        return tweets;
    }

    @Override
    public TweetDto saveOrUpdate(TweetDto tweetDto) {
        Tweet tweet = tweetRepository.save(TweetMapper.MAPPER.toTweet(tweetDto));
        return TweetMapper.MAPPER.fromTweet(tweet);
    }

    @Override
    public List<TweetDto> findAll() {
        return TweetMapper.MAPPER.fromTweetList(tweetRepository.findAll());
    }

    @Override
    public TweetDto findById(Long id) {
        return TweetMapper.MAPPER.fromTweet(tweetRepository.findById(id)
                .orElseThrow(() -> new NotFoundForTheRequestedParamException(id)));
    }

    @Override
    public Tweet doLike(final Long id) {
        final Tweet tweet = tweetRepository.findById(id).orElseThrow(() -> new RuntimeException("No such tweet found"));
        tweet.setLikes(tweet.getLikes() == null ? 1 : tweet.getLikes() + 1);
        return tweetRepository.save(tweet);
    }

    @Override
    public List<TweetDto> findOwnAndBySubcrTweetsByUserId(final Long id) {
        List<UserDto> userList = userService.getAllTheUserIsSubscribedTo(id);
        userList.add(userService.findById(id));
        final List<TweetDto> tweetDtos = new ArrayList<>();
        for (final UserDto user: userList
        ) {
            tweetRepository
                    .findAllByUserIdOrderByCreatedDesc(user.getId())
                    .ifPresent(list -> tweetDtos.addAll(list
                            .stream()
                            .map(TweetMapper.MAPPER::fromTweet)
                            .collect(Collectors.toList())));
        }
        return tweetDtos;
    }

    @Override
    public TweetDto findTopByUserIdByOrderByIdDesc(Long id) {
        return TweetMapper.MAPPER.fromTweet(tweetRepository.findTopByUserIdOrderByCreatedDesc(id).orElse(null));
    }

    @Override
    public List<TweetDto> findTop10ByOrderByIdDescUserId(Long id) {
        return TweetMapper.MAPPER.fromTweetList(tweetRepository.findFirst10ByUserIdOrderByCreatedDesc(id).orElse(null));
    }

    @Override
    public List<TweetDto> findAllByUser(UserDto user) {
        return TweetMapper.MAPPER.fromTweetList(tweetRepository.findAllByUser(UserMapper.MAPPER.toUser(user)).orElse(null));

    }

    @Override
    public List<TweetDto> findAllByOrderByCreatedDesc() {
        return TweetMapper.MAPPER.fromTweetList(tweetRepository.findAllByOrderByCreatedDesc());
    }

    @Override
    public void delete(Long id) {
        if (tweetRepository.findById(id).isPresent()) {
            tweetRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("ID: " +id+ " was found in the database");
            // TDOD: подправить error handling
        }
    }
}
