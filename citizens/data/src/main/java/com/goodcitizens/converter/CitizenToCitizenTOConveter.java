package com.goodcitizens.converter;

import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.log4j.Logger;

public class CitizenToCitizenTOConveter {

    final static Logger logger = Logger.getLogger(CitizenToCitizenTOConveter.class);

    public static CitizenTO convert(Citizen citizen){
        LogUtils.logDebug(logger, LogLevel.BUSINESS, LogUtilMsg.CONVERTER_FROM_ENTITY_TO);
        CitizenTO citizenTO = null;
        if(citizen != null){
            citizenTO = new CitizenTO();
            citizenTO.setCitizenId(citizen.getCitizenId());
            citizenTO.setName(citizen.getName());
            citizenTO.setSurname(citizen.getSurname());
            citizenTO.setNickname(citizen.getNickname());
            citizenTO.setEmail(citizen.getEmail());
            citizenTO.setPassword(citizen.getPassword());
            citizenTO.setCountry(citizen.getCountry());
        }
        return citizenTO;
    }
}
