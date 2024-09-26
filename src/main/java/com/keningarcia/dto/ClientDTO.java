package com.keningarcia.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {

    private int idClient;

    @NotNull
    @Size(min = 3, max = 10)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 10)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 10)
    private String country;

    @NotNull
    @Size(min = 10, max = 10)
    private String cardId;

    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String phoneNumber;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 3, max = 100)
    private String address;

}
