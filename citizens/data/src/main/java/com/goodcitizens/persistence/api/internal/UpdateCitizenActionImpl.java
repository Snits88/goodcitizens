package com.goodcitizens.persistence.api.internal;

import com.goodcitizens.converter.CitizenTOtoCitizenConveter;
import com.goodcitizens.converter.CitizenToCitizenTOConveter;
import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import com.goodcitizens.to.CitizenTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("updateCitizenAction")
public class UpdateCitizenActionImpl implements UpdateCitizenAction {

    @Autowired
    private CitizenBasicCrudRepository citizenRepository;

    @Override
    public CitizenTO update(String code, CitizenTO citizenTO) {
        Long c = Long.valueOf(StringUtils.trim(code));
        Citizen toUpdate = CitizenTOtoCitizenConveter.convert(citizenTO);
        toUpdate.setCitizenId(c);
        //TODO setCreateDate
        Citizen updated = citizenRepository.save(toUpdate);
        CitizenTO output = CitizenToCitizenTOConveter.convert(updated);
        return output;
    }

}
