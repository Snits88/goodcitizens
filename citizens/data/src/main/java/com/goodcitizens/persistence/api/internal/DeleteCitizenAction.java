package com.goodcitizens.persistence.api.internal;

@FunctionalInterface
public interface DeleteCitizenAction {

    void delete(String code);

}
