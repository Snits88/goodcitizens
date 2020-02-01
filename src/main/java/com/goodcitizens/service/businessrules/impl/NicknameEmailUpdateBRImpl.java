package com.goodcitizens.service.businessrules.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.service.businessrules.NicknameEmailUpdateBR;
import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.utils.ErrorMsg;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component("nicknameEmailUpdateBR")
public class NicknameEmailUpdateBRImpl implements NicknameEmailUpdateBR {

    final static Logger logger = Logger.getLogger(NicknameEmailUpdateBRImpl.class);

    public void validUpdate(Long c, CitizenListTO citizenListTO){
        LogUtils.logDebug(logger, LogLevel.BUSINESS, LogUtilMsg.CITIZEN_NICKNAME_EMAIL_UPDATE_VALIDATION);
        if(citizenListTO != null && citizenListTO.getCitizens() != null){
            List<CitizenTO> citizens = citizenListTO.getCitizens();
            if(!CollectionUtils.isEmpty(citizens)){
                for (CitizenTO citizen: citizens) {
                    if(!c.equals(citizen.getCitizenId())) {
                        throw new CitizenGenericExpection(ErrorMsg.NICKNAME_EMAIL_UPDATE_MSG, ErrorMsg.NICKNAME_EMAIL_UPDATE_CODE);
                    }
                }
            }
        }
    }
}
