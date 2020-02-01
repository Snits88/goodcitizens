package com.goodcitizens.service.businessrules;

import com.goodcitizens.to.CitizenTO;

@FunctionalInterface
public interface NormalizeInputFieldBR {

    CitizenTO normalize(CitizenTO citizenTO);
}
