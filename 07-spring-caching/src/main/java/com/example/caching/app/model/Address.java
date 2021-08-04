package com.example.caching.app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private Integer zipCode;

    @Column
    private String street;

    @Column
    private Integer flatNumber;

    @Builder.Default
    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<User> users = new HashSet<>();
}