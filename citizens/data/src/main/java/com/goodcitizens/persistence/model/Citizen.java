package com.goodcitizens.persistence.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "CITIZEN")
public class Citizen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "citizen_id", updatable = false, nullable = false)
    private Long citizenId;

    @NotEmpty
    @Column(name = "name", length = 50)
    private String name;

    @NotEmpty
    @Column(name = "surname", length = 50)
    private String surname;

    @NotEmpty
    @Column(name = "nickname", length = 50)
    private String nickname;

    @NotEmpty
    @Column(name = "password", length = 50)
    private String password;

    @NotEmpty
    @Column(name = "email", length = 50)
    private String email;

    @NotEmpty
    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

}
