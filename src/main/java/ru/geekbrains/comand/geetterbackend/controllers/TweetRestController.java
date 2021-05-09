package ru.geekbrains.comand.geetterbackend.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.comand.geetterbackend.entities.Tweet;
import ru.geekbrains.comand.geetterbackend.entities.dto.NewTweetDto;
import ru.geekbrains.comand.geetterbackend.entities.dto.TweetDto;
import ru.geekbrains.comand.geetterbackend.mappers.UserMapper;
import ru.geekbrains.comand.geetterbackend.services.interfaces.TweetService;
import ru.geekbrains.comand.geetterbackend.services.interfaces.UserService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/tweets")
@AllArgsConstructor
@Api(value = "Tweets")
public class TweetRestController {

    private final TweetService tweetService;
    private final UserService userService;

    @ApiOperation(value = "Get all tweets order by creation desc")
    @GetMapping
    public List<TweetDto> getAllTweets() {
        return tweetService.findAllByOrderByCreatedDesc();
    }

    @ApiOperation(value = "Create new tweet")
    @PostMapping
    public TweetDto createTweet(@RequestBody @Valid final NewTweetDto newTweetDto) {
        if (newTweetDto.getText() == null) {
            throw new IllegalArgumentException("No text found in tweet");
            // только создание никакого редактирования
        }
        final TweetDto tweetDto = new TweetDto();
        tweetDto.setCreated(LocalDateTime.now());
        tweetDto.setComments(new ArrayList<>());
        tweetDto.setUser(UserMapper.MAPPER.toUser(userService.findById(newTweetDto.getUserId())));
        tweetDto.setIsPublished(newTweetDto.isPublished());
        tweetDto.setText(newTweetDto.getText());

        return tweetService.saveOrUpdate(tweetDto);
    }

    @ApiOperation(value = "Do like")
    @GetMapping(path = "/{id}/like")
    public Tweet doLike(@PathVariable("id") final Long id) {
        return tweetService.doLike(id);
    }


    @ApiOperation(value = "Get tweet by id")
    @GetMapping(path = "/{id}/id")
    public TweetDto getTweetById(@PathVariable("id") final Long id) {
        return tweetService.findById(id);
    }

    @ApiOperation(value = "Get list of top 10 tweets from the db")
    @GetMapping(path = "/user/{id}/top10")
    public List<TweetDto> getTop10TweetByUserId(@PathVariable("id") final Long id) {
        return tweetService.findTop10ByOrderByIdDescUserId(id);
    }

    // TODO: ресты для пагинации / lasy loading-a можно сюда

    @ApiOperation(value = "Get all tweets by id")
    @GetMapping(path = "/user/{id}/all")
    public List<TweetDto> getAllTweetByUserId(@PathVariable("id") final Long id) {
         return tweetService.findAllByUser(userService.findById(id));
    }

    @ApiOperation(value = "Activate tweet by id")
    @GetMapping(path = "/{id}/activate")
    public void activateTweetById(@PathVariable("id") final Long id) {
        final TweetDto tweetDto = tweetService.findById(id);
        tweetDto.setIsPublished(true);
        tweetService.saveOrUpdate(tweetDto);
    }

    @ApiOperation(value = "Set tweet toinactivestate by id")
    @GetMapping(path = "/{id}/inactivate")
    public void inactivateTweetById(@PathVariable("id") Long id) {
        final TweetDto tweetDto = tweetService.findById(id);
        tweetDto.setIsPublished(false);
        tweetService.saveOrUpdate(tweetDto);
    }

    @GetMapping(path = "/{id}/ownandsubscr")
    public List<TweetDto> getOwnAndBySubscrTweets(@PathVariable("id") Long id) {
        return tweetService.findOwnAndBySubcrTweetsByUserId(id);
    }

    // TODO: подумать над деактивацией стразу у всех твитов юзера

    // TODO: сюда добавить работу с коментариями

    @ApiOperation(value = "Delete tweet by id")
    @DeleteMapping(path = "/{id}/id")
    public void deleteTweetById(@PathVariable("id") Long id) {
        tweetService.delete(id);
    }

    // TODO: подумать над удалением всех твитов юзера
}
