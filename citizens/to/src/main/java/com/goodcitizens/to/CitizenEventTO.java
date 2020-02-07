package com.goodcitizens.to;

import java.util.Objects;

public class CitizenEventTO {

    private Long timestamp;

    private String correlationId;

    private Long citizenId;

    private String name;

    private String surname;

    private String nickname;

    private String password;

    private String email;

    private String country;

    public Long getTimestamp() {
        return timestamp;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CitizenEventTO)) return false;
        CitizenEventTO that = (CitizenEventTO) o;
        return getTimestamp().equals(that.getTimestamp()) &&
                getCorrelationId().equals(that.getCorrelationId()) &&
                getCitizenId().equals(that.getCitizenId()) &&
                getName().equals(that.getName()) &&
                getSurname().equals(that.getSurname()) &&
                getNickname().equals(that.getNickname()) &&
                getPassword().equals(that.getPassword()) &&
                getEmail().equals(that.getEmail()) &&
                getCountry().equals(that.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTimestamp(), getCorrelationId(), getCitizenId(), getName(), getSurname(), getNickname(), getPassword(), getEmail(), getCountry());
    }

    @Override
    public String toString() {
        return "CitizenEventTO{" +
                "timestamp=" + timestamp +
                ", correlationId='" + getCorrelationId() + '\'' +
                ", citizenId=" + citizenId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
