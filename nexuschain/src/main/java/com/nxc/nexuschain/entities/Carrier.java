package com.nxc.nexuschain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "carrier")
public class Carrier {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private User user;

    @Column(name = "rating")
    private Float rating;

    @Lob
    @Column(name = "cooperation_terms")
    private String cooperationTerms;

    @OneToMany(mappedBy = "carrier")
    private Set<Delivery> deliveries = new LinkedHashSet<>();

}