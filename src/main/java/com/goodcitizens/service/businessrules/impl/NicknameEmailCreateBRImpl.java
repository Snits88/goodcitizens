package com.goodcitizens.service.businessrules.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.service.businessrules.NicknameEmailCreateBR;
import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.utils.ErrorMsg;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("nicknameEmailCreateBR")
public class NicknameEmailCreateBRImpl implements NicknameEmailCreateBR {

    final static Logger logger = Logger.getLogger(NicknameEmailCreateBRImpl.class);

    @Override
    public void validCreation(CitizenListTO citizenListTO) {
        LogUtils.logDebug(logger, LogLevel.BUSINESS, LogUtilMsg.CITIZEN_NICKNAME_EMAIL_CREATE_VALIDATION);
        if(citizenListTO != null && citizenListTO.getCitizens() != null){
            List<CitizenTO> citizens = citizenListTO.getCitizens();
            if(citizens.size() > 0){
                throw new CitizenGenericExpection(ErrorMsg.NICKNAME_EMAIL_CREATE_MSG, ErrorMsg.NICKNAME_EMAIL_CREATE_CODE);
            }
        }
    }
}
