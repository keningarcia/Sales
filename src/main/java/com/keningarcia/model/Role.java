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
public class Role {

    @Id
    @EqualsAndHashCode.Include
    private Integer idRole;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false)
    private boolean  enabled;
}
