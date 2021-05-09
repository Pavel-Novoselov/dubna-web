package ru.geekbrains.comand.geetterbackend.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.comand.geetterbackend.entities.Role;
import ru.geekbrains.comand.geetterbackend.entities.User;
import ru.geekbrains.comand.geetterbackend.entities.dto.UserDto;
import ru.geekbrains.comand.geetterbackend.exceptions.NotFoundForTheRequestedParamException;
import ru.geekbrains.comand.geetterbackend.exceptions.ResourceNotFoundException;
import ru.geekbrains.comand.geetterbackend.mappers.UserMapper;
import ru.geekbrains.comand.geetterbackend.repositories.RoleRepository;
import ru.geekbrains.comand.geetterbackend.repositories.UserRepository;
import ru.geekbrains.comand.geetterbackend.services.interfaces.UserService;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto saveOrUpdate(UserDto userDto) {
        User user = UserMapper.MAPPER.toUser(userDto);
        if ((userDto.getPassword() == null && userDto.getId() != null)) {
            user.setPassword(userRepository.getOne(userDto.getId()).getPassword());
        } else {
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        if (userDto.getRoles() == null && roleRepository.findById(1L).isPresent()) {
            Set<Role> userExistingRoles = user.getRoles();
            userExistingRoles.add(roleRepository.findById(1L).get());
            user.setRoles(userExistingRoles);
        }
        userRepository.save(user);
        return UserMapper.MAPPER.fromUser(user);
    }

    @Override
    public List<UserDto> findAll() {
        final List<User> userList = userRepository.findAll();
        return userList.stream().map(UserMapper.MAPPER::fromUser).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        return UserMapper.MAPPER.fromUser(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundForTheRequestedParamException(id)));
    }

    @Override
    public UserDto findOneByUsername(String username) {
        return UserMapper.MAPPER.fromUser(userRepository.findUserByUsername(username)
                .orElseThrow(() -> new NotFoundForTheRequestedParamException(username)));
    }

    @Override
    public UserDto findOneByEmail(String email) {
        return UserMapper.MAPPER.fromUser(userRepository.findOneByEmail(email)
                .orElseThrow(() -> new NotFoundForTheRequestedParamException(email)));
    }

    @Override
    public UserDto findOneByPhone(String phone) {
        return UserMapper.MAPPER.fromUser(userRepository.findOneByPhone(phone)
                .orElseThrow(() -> new NotFoundForTheRequestedParamException(phone)));
    }

    @Override
    public void delete(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new NotFoundForTheRequestedParamException(id);
        }
    }

    public User getByUsername(String username) {
        return userRepository.findOneByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Пользователь '%s' не найден", username)));
    }

    @Override
    public void subscribe(Long userId, Long targetUserId) {
        User user = userRepository.getOne(userId);
        User targetUser = userRepository.getOne(targetUserId);

        if (!user.getSubscriptions().contains(targetUser))
            user.getSubscriptions().add(targetUser);

        userRepository.save(user);
    }

    @Override
    public void unsubscribe(Long userId, Long targetUserId) {
        User user = userRepository.getOne(userId);
        User targetUser = userRepository.getOne(targetUserId);

        user.getSubscriptions().remove(targetUser);

        userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllTheUserIsSubscribedTo(final Long userId) {
        final List<User> userList = userRepository.findById(userId).get().getSubscriptions();
        return userList.stream().map(UserMapper.MAPPER::fromUser).collect(Collectors.toList());
    }

}
