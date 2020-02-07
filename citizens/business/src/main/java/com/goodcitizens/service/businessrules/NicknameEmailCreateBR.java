package com.goodcitizens.service.businessrules;

import com.goodcitizens.to.CitizenListTO;

@FunctionalInterface
public interface NicknameEmailCreateBR {

    void validCreation(CitizenListTO citizenListTO);

}
