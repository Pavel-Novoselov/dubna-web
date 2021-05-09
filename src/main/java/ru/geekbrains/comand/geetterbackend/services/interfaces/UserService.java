package ru.geekbrains.comand.geetterbackend.services.interfaces;

import org.springframework.stereotype.Service;
import ru.geekbrains.comand.geetterbackend.entities.dto.UserDto;

import java.util.List;

@Service
public interface UserService {

    UserDto saveOrUpdate(UserDto userDto);

    List<UserDto> findAll();

    UserDto findById(Long id);

    UserDto findOneByUsername(String username);

    UserDto findOneByEmail(String email);

    UserDto findOneByPhone(String phone);

    void delete(Long id);

    void subscribe(Long userId, Long targetUserId);

    void unsubscribe(Long userId, Long targetUserId);

    List<UserDto> getAllTheUserIsSubscribedTo(Long userId);


}
