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
public class Ingress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idIngress;

    @ManyToOne
    @JoinColumn(name = "id_provider", nullable = false, foreignKey = @ForeignKey(name = "FK_INGRESS_PROVIDER"))
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "id_userr", nullable = false, foreignKey = @ForeignKey(name = "FK_INGRESS_USER"))
    private User user;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double total;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double tax;

    @Column(nullable = false)
    private boolean enabled;
}
