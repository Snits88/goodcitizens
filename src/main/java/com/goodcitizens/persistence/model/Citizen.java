package com.goodcitizens.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Citizen)) return false;
        Citizen citizen = (Citizen) o;
        return getCitizenId().equals(citizen.getCitizenId()) &&
                getName().equals(citizen.getName()) &&
                getSurname().equals(citizen.getSurname()) &&
                getNickname().equals(citizen.getNickname()) &&
                getPassword().equals(citizen.getPassword()) &&
                getEmail().equals(citizen.getEmail()) &&
                getCountry().equals(citizen.getCountry()) &&
                getCreateDate().equals(citizen.getCreateDate()) &&
                getUpdateDate().equals(citizen.getUpdateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCitizenId(), getName(), getSurname(), getNickname(), getPassword(), getEmail(), getCountry(), getCreateDate(), getUpdateDate());
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "citizenId=" + citizenId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
