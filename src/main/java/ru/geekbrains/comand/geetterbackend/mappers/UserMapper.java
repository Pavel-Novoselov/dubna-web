package ru.geekbrains.comand.geetterbackend.mappers;



import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import ru.geekbrains.comand.geetterbackend.entities.dto.UserDto;
import ru.geekbrains.comand.geetterbackend.entities.User;

@Mapper
public interface UserMapper {

	UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

	@InheritInverseConfiguration
	User toUser(UserDto dto);

	UserDto fromUser(User user);
}
