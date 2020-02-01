package com.goodcitizens.to;

import java.util.Objects;

public class CitizenFilterTO {

    private String name;

    private String surname;

    private String nickname;

    private String email;

    private String country;

    private boolean enableOR;

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

    public boolean isEnableOR() {
        return enableOR;
    }

    public void setEnableOR(boolean enableOR) {
        this.enableOR = enableOR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CitizenFilterTO)) return false;
        CitizenFilterTO that = (CitizenFilterTO) o;
        return isEnableOR() == that.isEnableOR() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getSurname(), that.getSurname()) &&
                Objects.equals(getNickname(), that.getNickname()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getCountry(), that.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname(), getNickname(), getEmail(), getCountry(), isEnableOR());
    }

    @Override
    public String toString() {
        return "CitizenFilterTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", enableOR=" + enableOR +
                '}';
    }
}
