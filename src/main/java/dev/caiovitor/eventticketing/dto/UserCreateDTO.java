package dev.caiovitor.eventticketing.dto;

public record UserCreateDTO(
        String name,
        String password,
        String cpf,
        String email,
        String cep,
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state
) {
}
