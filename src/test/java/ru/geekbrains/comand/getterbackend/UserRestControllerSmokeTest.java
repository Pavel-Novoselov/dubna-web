package ru.geekbrains.comand.getterbackend;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.geekbrains.comand.geetterbackend.GeetterBackendApplication;
import ru.geekbrains.comand.geetterbackend.controllers.UserRestController;
import ru.geekbrains.comand.geetterbackend.entities.dto.UserDto;
import ru.geekbrains.comand.geetterbackend.entities.User;
import ru.geekbrains.comand.geetterbackend.repositories.UserRepository;
import ru.geekbrains.comand.geetterbackend.services.UserServiceImpl;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static ru.geekbrains.comand.geetterbackend._data.DataInitializer.NUMBER_OF_DEFAULTS_USERS;


@SpringBootTest(classes = GeetterBackendApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRestControllerSmokeTest {

    private static final Long USER_ID = NUMBER_OF_DEFAULTS_USERS + 1L;
    private static final String USER_NAME = "testUser";
    private static final String USER_EMAIL = "test@test.com";
    private static final String USER_PHONE = "123456789";
    private static final String USER_PASSWORD = "000";

    @Autowired
    private UserRestController userRestController;

    @Autowired
    private UserRepository userRepository;

    private static UserDto newUser;
    private static UserDto userFromResponse;

    @BeforeAll
    public static void init() {
        newUser = new UserDto();
        newUser.setUsername(USER_NAME);
        newUser.setPassword(USER_PASSWORD);
    }

    @Test
    @Order(1)
    public void checkUserController() {
        assertThat(userRestController).isNotNull();
        assertEquals(NUMBER_OF_DEFAULTS_USERS, userRestController.getAllUsers().size());
    }

    @Test
    @Order(2)
    public void addingUser() {
        userRestController.createUser(newUser);
        assertFalse(userRestController.getAllUsers().isEmpty());
        assert (userRestController.getAllUsers().size() == NUMBER_OF_DEFAULTS_USERS + 1L);
    }

    @Test
    @Order(3)
    public void gettingUserById() {
        userFromResponse = userRestController.getUserById(USER_ID);
        assert userFromResponse != null;
        assertEquals(newUser.getUsername(), userFromResponse.getUsername());
    }

    @Test
    @Order(4)
    public void gettingUserByUsername() {
        userFromResponse = userRestController.getUserByUsername(USER_NAME);
        assertEquals(newUser.getUsername(), Objects.requireNonNull(userFromResponse).getUsername());
    }

    @Test
    @Order(5)
    public void updatingUser() {
        newUser.setId(USER_ID);
        newUser.setEmail(USER_EMAIL);
        newUser.setPhone(USER_PHONE);
        userRestController.updateUser(newUser);
        assertFalse(userRestController.getAllUsers().isEmpty());
    }

    @Test
    @Order(6)
    public void gettingUserByEmail() {
        userFromResponse = userRestController.getUserByEmail(USER_EMAIL);
        assertEquals(newUser.getEmail(), Objects.requireNonNull(userFromResponse).getEmail());
    }

    @Test
    @Order(7)
    public void gettingUserByPhone() {
        userFromResponse = userRestController.getUserByPhone(USER_PHONE);
        assertEquals(newUser.getPhone(), Objects.requireNonNull(userFromResponse).getPhone());
    }

    @Test
    @Order(8)
    public void deletingUser() {
        UserDto userToBeDeleted = userRestController.getUserById(USER_ID);
        userRestController.deleteUser(USER_ID);
        assertFalse(userRestController.getAllUsers().contains(userToBeDeleted));
    }

    @Test
    @Order(9)
    public void subscribe() {
        User user = userRepository.save(User.builder().username(USER_NAME).enabled(true).password(USER_PASSWORD).build());
        User target = userRepository.save(User.builder().username("targetUser").enabled(true).password("pwned").build());

        userRestController.subscribe(user.getId(), target.getId());

        user = userRepository
                .findOneByIdWithSubscriptions(user.getId())
                .orElse(new User());

        assertEquals(user.getSubscriptions().size(), 1);
        assertEquals(user.getSubscriptions().get(0).getId(), target.getId());
    }

    @Test
    @Order(10)
    public void getSubscriptions() {
        User user = userRepository.findOneByUsername(USER_NAME).orElse(new User());

        assertFalse(userRestController.getAllTheUserIsSubscribedTo(user.getId()).isEmpty());
    }

    @Test
    @Order(11)
    public void unubscribe() {
        User user = userRepository.findOneByUsername(USER_NAME).orElse(new User());
        User target = userRepository.findOneByUsername("targetUser").orElse(new User());

        userRestController.unsubscribe(user.getId(), target.getId());

        assertTrue(userRestController.getAllTheUserIsSubscribedTo(user.getId()).isEmpty());
    }

}
