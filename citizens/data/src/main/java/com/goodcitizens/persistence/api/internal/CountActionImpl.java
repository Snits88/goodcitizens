package com.goodcitizens.persistence.api.internal;

import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("countAction")
public class CountActionImpl implements CountAction {

    @Autowired
    private CitizenBasicCrudRepository citizenRepository;

    @Override
    public Long count() {
        return citizenRepository.count();
    }

}
