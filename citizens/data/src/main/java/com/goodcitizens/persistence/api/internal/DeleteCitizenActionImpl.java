package com.goodcitizens.persistence.api.internal;

import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component("deleteCitizenAction")
public class DeleteCitizenActionImpl implements DeleteCitizenAction {

    @Autowired
    private CitizenBasicCrudRepository citizenRepository;

    @Override
    public void delete(String code) {
        Long c = Long.valueOf(StringUtils.trim(code));
        citizenRepository.deleteById(c);
    }

}
