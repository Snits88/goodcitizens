package com.goodcitizens.persistence.api.internal;

import com.goodcitizens.converter.CitizenTOtoCitizenConveter;
import com.goodcitizens.converter.CitizenToCitizenTOConveter;
import com.goodcitizens.persistence.model.Citizen;

import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import com.goodcitizens.to.CitizenTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("createCitizenAction")
public class CreateCitizenActionImpl implements CreateCitizenAction {

    @Autowired
    private CitizenBasicCrudRepository citizenRepository;

    @Override
    public CitizenTO create(CitizenTO citizen) {
        Citizen toCreate = CitizenTOtoCitizenConveter.convert(citizen);
        Citizen created = citizenRepository.save(toCreate);
        CitizenTO output = CitizenToCitizenTOConveter.convert(created);
        return output;
    }

}
