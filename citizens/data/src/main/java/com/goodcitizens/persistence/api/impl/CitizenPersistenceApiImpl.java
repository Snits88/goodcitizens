package com.goodcitizens.persistence.api.impl;

import com.goodcitizens.persistence.api.CitizenPersistenceApi;
import com.goodcitizens.persistence.api.internal.*;
import com.goodcitizens.to.CitizenFilterTO;
import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.to.CitizenTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("citizenPersistenceApi")
public class CitizenPersistenceApiImpl implements CitizenPersistenceApi {

    @Autowired
    private UpdateCitizenAction updateCitizenAction;

    @Autowired
    private DeleteCitizenAction deleteCitizenAction;

    @Autowired
    private CreateCitizenAction createCitizenAction;

    @Autowired
    private ReadCitizenListAction readCitizenListAction;

    @Autowired
    private ReadByCodeAction readByCodeAction;

    @Autowired
    private CountAction countAction;

    @Override
    public Long count() {
        return countAction.count();
    }

    @Override
    public CitizenTO readByCode(String code) {
        return readByCodeAction.read(code);
    }

    @Override
    public CitizenListTO readCitizensList(CitizenFilterTO searchFilter) {
        return readCitizenListAction.read(searchFilter);
    }

    @Override
    public CitizenTO createCitizen(CitizenTO citizenTO) {
        return createCitizenAction.create(citizenTO);
    }

    @Override
    public CitizenTO updateCitizen(String code, CitizenTO citizenTO) {
        return updateCitizenAction.update(code, citizenTO);
    }

    @Override
    public void deleteCitizen(String code) {
        deleteCitizenAction.delete(code);
    }

}
