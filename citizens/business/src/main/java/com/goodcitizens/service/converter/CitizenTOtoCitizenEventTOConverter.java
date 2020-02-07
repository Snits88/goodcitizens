package com.goodcitizens.service.converter;

import com.goodcitizens.to.CitizenEventTO;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.log4j.Logger;

public class CitizenTOtoCitizenEventTOConverter {

    final static Logger logger = Logger.getLogger(CitizenTOtoCitizenEventTOConverter.class);

    public static CitizenEventTO convert(CitizenTO citizenTO){
        LogUtils.logDebug(logger, LogLevel.BUSINESS, LogUtilMsg.CONVERTER_FROM_TO_EVENT);
        CitizenEventTO citizenEventTO = null;
        if(citizenTO != null){
            citizenEventTO = new CitizenEventTO();
            citizenEventTO.setTimestamp(System.currentTimeMillis());
            citizenEventTO.setCitizenId(citizenTO.getCitizenId());
            citizenEventTO.setName(citizenTO.getName());
            citizenEventTO.setSurname(citizenTO.getSurname());
            citizenEventTO.setNickname(citizenTO.getNickname());
            citizenEventTO.setEmail(citizenTO.getEmail());
            citizenEventTO.setPassword(citizenTO.getPassword());
            citizenEventTO.setCountry(citizenTO.getCountry());
        }
        return citizenEventTO;
    }

}
