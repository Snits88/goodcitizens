package com.goodcitizens.to;

import lombok.Data;

@Data
public class CitizenFilterTO {

    private String name;

    private String surname;

    private String nickname;

    private String email;

    private String country;

    private boolean enableOR;

    private String offset;

    private String limit;

    private String orderBy;

    private boolean orderAsc;

}
