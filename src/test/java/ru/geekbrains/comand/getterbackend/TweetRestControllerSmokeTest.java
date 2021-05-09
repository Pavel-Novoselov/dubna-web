package ru.geekbrains.comand.getterbackend;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.comand.geetterbackend.GeetterBackendApplication;
import ru.geekbrains.comand.geetterbackend.controllers.TweetRestController;
import ru.geekbrains.comand.geetterbackend.controllers.UserRestController;
import ru.geekbrains.comand.geetterbackend.entities.dto.NewTweetDto;
import ru.geekbrains.comand.geetterbackend.entities.dto.TweetDto;
import ru.geekbrains.comand.geetterbackend.entities.User;
import ru.geekbrains.comand.geetterbackend.mappers.UserMapper;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.geekbrains.comand.geetterbackend._data.DataInitializer.NUMBER_OF_DEFAULTS_TWEETS;

@SpringBootTest(classes = GeetterBackendApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TweetRestControllerSmokeTest {

    private static NewTweetDto newTweet;
    private static TweetDto tweetFromResponse;
    private static User newUser;
    private static List<TweetDto> tweetDtoListFromResponse;
    private static Long newTweetId;

    @Autowired
    private TweetRestController tweetRestController;

    @Autowired
    private UserRestController userRestController;

    @BeforeAll
    public static void init() {
        newTweet = new NewTweetDto();
    }

    @Test
    @Order(1)
    public void checkTweetController() {
        assertThat(tweetRestController).isNotNull();
        assert (tweetRestController.getAllTweets().size() == NUMBER_OF_DEFAULTS_TWEETS);
    }

    @Test
    @Order(2)
    public void addingNewTweet() {
        newUser = UserMapper.MAPPER.toUser(userRestController.getUserById(2L));
        //newTweet = TweetMapper.MAPPER.fromTweet(Tweet.builder().user(newUser).text("Smoke test tweet").created(LocalDateTime.now()).isPublished(true).build());
        newTweet.setUserId(newUser.getId());
        newTweet.setText("Smoke test tweet");
        newTweet.setPublished(true);
        tweetRestController.createTweet(newTweet);
        newTweetId = NUMBER_OF_DEFAULTS_TWEETS + 1L;
        assert (tweetRestController.getAllTweets().size() == NUMBER_OF_DEFAULTS_TWEETS + 1L);
    }

    @Test
    @Order(3)
    public void gettingTweetById() {
        tweetFromResponse = tweetRestController.getTweetById(newTweetId);
        assert (tweetFromResponse != null);
        assertEquals(tweetFromResponse.getUser().getId(), newTweet.getUserId());
        assertEquals(tweetFromResponse.getText(), newTweet.getText());
        assertEquals(tweetFromResponse.getIsPublished(), newTweet.isPublished());

    }

    @Test
    @Order(4)
    public void gettingTop10TweetByUserId() {
        for (int i = 0; i < 10; i++) {
            NewTweetDto newTweet = new NewTweetDto(newUser.getId(), "Tweet number " + i + " for user " + newUser.getUsername(), true);
            tweetRestController.createTweet(newTweet);
        }
        tweetDtoListFromResponse = tweetRestController.getTop10TweetByUserId(newUser.getId());
        assert (tweetDtoListFromResponse.size() >= 10);
        for (TweetDto tweetDto : tweetDtoListFromResponse) {
        	assertEquals(tweetDto.getUser().getId(), newUser.getId());
        }
    }

    @Test
    @Order(5)
    public void gettingAllTweetByUserId() {
        tweetDtoListFromResponse = tweetRestController.getAllTweetByUserId(newUser.getId());
        assert (tweetDtoListFromResponse.size() >= (10 + 1));
        for (TweetDto tweetDto : tweetDtoListFromResponse) {
        	assertEquals(tweetDto.getUser().getId(), newUser.getId());
        }
    }

    @Test
    @Order(6)
    public void inactivateTweetById() {
        tweetRestController.inactivateTweetById(newTweetId);
        assertFalse(tweetRestController.getTweetById(newTweetId).getIsPublished());
    }

    @Test
    @Order(7)
    public void activateTweetById() {
        tweetRestController.inactivateTweetById(newTweetId);
        assertEquals(tweetRestController.getTweetById(newTweetId).getIsPublished(), false);
    }

//    @Test
//    @Order(7)
//    public void updateTweet() {
//        TweetDto updatedTweet = newTweet;
//        updatedTweet.setIsPublished(false);
//        updatedTweet.setCreated(LocalDateTime.now());
//        updatedTweet.setText("Edited text");
//        // updatedTweet.setComments(List<Comment> comment); after adding comments
//        tweetRestController.updateTweet(updatedTweet);
//        tweetDtoListFromResponse = tweetRestController.getAllTweetByUserId(newUser.getId());
//        assertEquals(newTweetId, tweetFromResponse.getId());
//        assertEquals(newTweet.getUser(), tweetFromResponse.getUser());
//        assertNotEquals(tweetFromResponse.getText(), newTweet.getText());
//        assertThat(tweetFromResponse.getCreated().getSecond())
//                .isCloseTo(newTweet.getCreated().getSecond(), withPercentage(1));
////        assertThat(tweetFromResponse.getCreated().isCloseTo(newTweet.getCreated()));
//        assertNotEquals(tweetFromResponse.getIsPublished(), newTweet.getIsPublished());
//    }

    @Test
    @Order(8)
    public void deleteTweetById() {
        int tweetCount = tweetRestController.getAllTweets().size();
        tweetRestController.deleteTweetById(newTweetId);
        assert (!tweetRestController.getAllTweets().contains(newTweet));
        assert ((tweetCount - 1) == tweetRestController.getAllTweets().size());
    }

}

