package dev.caiovitor.eventticketing.mapper;

import dev.caiovitor.eventticketing.dto.UserCreateDTO;
import dev.caiovitor.eventticketing.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "cep", target = "address.cep")
    @Mapping(source = "street",target = "address.street")
    @Mapping(source = "number",target = "address.number")
    @Mapping(source = "complement",target = "address.complement")
    @Mapping(source = "neighborhood",target = "address.neighborhood")
    @Mapping(source = "city",target = "address.city")
    @Mapping(source = "state",target = "address.state")
    public User toEntity(UserCreateDTO dto);

}
