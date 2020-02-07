package com.goodcitizens.persistence.api.internal;

import com.goodcitizens.converter.CitizenListToCitizenListTOConverter;
import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import com.goodcitizens.to.CitizenFilterTO;
import com.goodcitizens.to.CitizenListTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("readCitizenListAction")
public class ReadCitizenListActionImpl implements ReadCitizenListAction {

    @Autowired
    private CitizenBasicCrudRepository citizenRepository;

    @Override
    public CitizenListTO read(CitizenFilterTO searchFilter) {
        List<Citizen> result = citizenRepository.readCitizensList(searchFilter);
        return CitizenListToCitizenListTOConverter.convert(result);
    }

}
