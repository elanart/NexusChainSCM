package com.nxc.nexuschain.entity;

import com.nxc.nexuschain.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "full_name", length = 50, nullable = false)
    private String fullName;

    @Column(length = 300)
    private String address;

    @Column(length = 12)
    private String phone;

    @Column(length = 300)
    private String avatar;

    @Column(length = 200)
    private String email;

    @Builder.Default
    @Column(name = "deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_date",updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", insertable = false)
    private LocalDateTime updatedDate;

    @Valid
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "user")
    private Account account;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;
}
