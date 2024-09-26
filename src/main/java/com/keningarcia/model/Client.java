package com.keningarcia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idClient;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 10, unique = true)
    private String cardId;

    @Column(nullable = false, length = 10)
    private String phoneNumber;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(nullable = false, length = 35)
    private String country;
}
