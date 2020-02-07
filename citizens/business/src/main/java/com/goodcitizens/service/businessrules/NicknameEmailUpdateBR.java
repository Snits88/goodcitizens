package com.goodcitizens.service.businessrules;

import com.goodcitizens.to.CitizenListTO;

@FunctionalInterface
public interface NicknameEmailUpdateBR {

    void validUpdate(Long c, CitizenListTO citizenListTO);

}
