package com.goodcitizens.persistence.api.internal;

import com.goodcitizens.converter.CitizenToCitizenTOConveter;
import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import com.goodcitizens.to.CitizenTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("readByCodeAction")
public class ReadByCodeActionImpl implements ReadByCodeAction {

    @Autowired
    private CitizenBasicCrudRepository citizenRepository;

    @Override
    public CitizenTO read(String code) {
        CitizenTO output = null;
        Long c = Long.valueOf(StringUtils.trim(code));
        Optional<Citizen> citizenOp = citizenRepository.findById(c);
        if(citizenOp.isPresent()) {
            output = CitizenToCitizenTOConveter.convert(citizenOp.get());
        }
        return output;
    }

}
