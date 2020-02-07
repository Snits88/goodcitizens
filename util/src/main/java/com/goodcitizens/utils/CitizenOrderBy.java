package com.goodcitizens.utils;

public enum CitizenOrderBy {

    NAME("name"),
    SURNAME("surname"),
    NICKNAME("nickname");

    private String order;

    CitizenOrderBy(String value) {
        setOrder(value);
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

}
