package com.goodcitizens.persistence.api.internal;

import com.goodcitizens.to.CitizenTO;

@FunctionalInterface
public interface ReadByCodeAction {

    CitizenTO read(String code);

}
