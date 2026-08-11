package dev.caiovitor.eventticketing.mapper;

import dev.caiovitor.eventticketing.dto.UserCreateDTO;
import dev.caiovitor.eventticketing.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthenticationMapper {

    public User toEntity(UserCreateDTO dto);
}
