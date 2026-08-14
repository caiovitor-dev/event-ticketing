package dev.caiovitor.eventticketing.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UserCreateDTO(

        @NotBlank(message = "Name cannot be blank.")
        @Size(message = "Name can have a maximum of 80 characters.", max = 80)
        String name,

        @NotBlank(message = "Password cannot be blank")
        String password,

        @NotBlank(message = "CPF cannot be blank")
        @CPF(message = "The provided CPF is invalid.")
        String cpf,

        @NotBlank(message = "Email cannot be blank")
        String email,

        @NotBlank(message = "CEP cannot be blank")
        @Pattern(regexp = "\\d{8}",message = "CEP must contain exactly 8 digits")
        String cep,

        @NotBlank(message = "Street cannot be blank")
        String street,

        @NotBlank(message = "Number cannot be blank")
        String number,

        @NotBlank(message = "Complement cannot be blank")
        String complement,

        @NotBlank(message = "Neighborhood cannot be blank")
        String neighborhood,

        @NotBlank(message = "City cannot be blank")
        String city,

        @NotBlank(message = "State cannot be blank")
        String state
) {
}
