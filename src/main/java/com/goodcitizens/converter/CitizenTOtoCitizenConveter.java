package com.goodcitizens.converter;

import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

public class CitizenTOtoCitizenConveter {

    final static Logger logger = Logger.getLogger(CitizenTOtoCitizenConveter.class);

    public static Citizen convert(CitizenTO citizenTO){
        LogUtils.logDebug(logger, LogLevel.BUSINESS, LogUtilMsg.CONVERTER_FROM_TO_ENTITY);
        Citizen citizen = null;
        if(citizenTO != null){
            citizen = new Citizen();
            LocalDateTime time = LocalDateTime.now();
            citizen.setCitizenId(citizenTO.getCitizenId());
            citizen.setName(citizenTO.getName());
            citizen.setSurname(citizenTO.getSurname());
            citizen.setNickname(citizenTO.getNickname());
            citizen.setEmail(citizenTO.getEmail());
            citizen.setPassword(citizenTO.getPassword());
            citizen.setCountry(citizenTO.getCountry());
            citizen.setCreateDate(time);
            citizen.setUpdateDate(time);
        }
        return citizen;
    }

}
