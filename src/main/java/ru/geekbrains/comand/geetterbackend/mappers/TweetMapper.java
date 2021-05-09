package ru.geekbrains.comand.geetterbackend.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.comand.geetterbackend.entities.dto.TweetDto;
import ru.geekbrains.comand.geetterbackend.entities.Tweet;

import java.util.List;

@Mapper
public interface TweetMapper {

    TweetMapper MAPPER = Mappers.getMapper(TweetMapper.class);

    Tweet toTweet(TweetDto dto);

    @InheritInverseConfiguration
    TweetDto fromTweet(Tweet tweet);

    List<Tweet> toTweetList(List<TweetDto> dtoList);

    List<TweetDto> fromTweetList(List<Tweet> TweetList);
}
