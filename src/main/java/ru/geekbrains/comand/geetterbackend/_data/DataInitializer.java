package ru.geekbrains.comand.geetterbackend._data;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.geekbrains.comand.geetterbackend.entities.Comment;
import ru.geekbrains.comand.geetterbackend.entities.Role;
import ru.geekbrains.comand.geetterbackend.entities.Tweet;
import ru.geekbrains.comand.geetterbackend.entities.User;
import ru.geekbrains.comand.geetterbackend.repositories.CommentRepository;
import ru.geekbrains.comand.geetterbackend.repositories.RoleRepository;
import ru.geekbrains.comand.geetterbackend.repositories.TweetRepository;
import ru.geekbrains.comand.geetterbackend.repositories.UserRepository;
import ru.geekbrains.comand.geetterbackend.services.UserServiceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    public static final int NUMBER_OF_DEFAULTS_USERS = 50;
    public static final int NUMBER_OF_DEFAULTS_TWEETS = 50;


    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final TweetRepository tweetRepository;
    private final CommentRepository commentRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public void run(final String... args) {

        //roles
        final List<Role> rolesToAdd = new ArrayList<>();
        rolesToAdd.add(Role.builder().id(1L).name("ROLE_USER").build());
        rolesToAdd.add(Role.builder().id(2L).name("ROLE_ADMIN").build());

        roleRepository.saveAll(rolesToAdd);

        final List<User> usersToAdd = new ArrayList<>();
        // setup Admin
        final User admin = User.builder().username("admin").password(passwordEncoder.encode("pass")).firstName("Admin").lastName("Adminich").enabled(true).roles(Collections.singleton(roleRepository.findById(2L).get())).build();
        usersToAdd.add(admin);

        // users
        for (int i = 1; i < NUMBER_OF_DEFAULTS_USERS; i++) {
            final User user = User.builder().username("user" + i).password(passwordEncoder.encode("pass" + i)).firstName("Vasya" + i).lastName("Pupkin" + i).enabled(true).roles(Collections.singleton(roleRepository.findById(1L).get())).build();
            usersToAdd.add(user);
        }

        userRepository.saveAll(usersToAdd);

        //tweets
        final List<Tweet> tweetsToAdd = new ArrayList<>();
        final List<Comment> commentList = new ArrayList<>();
        //adding NUMBER_OF_DEFAULTS_TWEETS to user number 1
        for (int i = 0; i < NUMBER_OF_DEFAULTS_TWEETS; i++) {
            final Tweet tweet = Tweet.builder().user(usersToAdd.get(1)).text("Tweet number " + i + " for user " + usersToAdd.get(1).getUsername()).created(LocalDateTime.now()).isPublished(true).build();
            tweetsToAdd.add(tweet);
            final Comment comment = new Comment(usersToAdd.get(1), tweet, LocalDateTime.now(), "comment" + i);
            commentList.add(comment);
            Thread.sleep(100);
        }


        tweetRepository.saveAll(tweetsToAdd);
        commentRepository.saveAll(commentList);
    }
}
