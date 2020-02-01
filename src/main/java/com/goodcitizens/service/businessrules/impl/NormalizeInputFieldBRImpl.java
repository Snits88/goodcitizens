package com.goodcitizens.service.businessrules.impl;

import com.goodcitizens.service.businessrules.NormalizeInputFieldBR;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("normalizeInputFieldBR")
public class NormalizeInputFieldBRImpl implements NormalizeInputFieldBR {

    final static Logger logger = Logger.getLogger(NormalizeInputFieldBRImpl.class);

    @Override
    public CitizenTO normalize(CitizenTO input) {
        LogUtils.logDebug(logger, LogLevel.BUSINESS, LogUtilMsg.CITIZEN_INPUT_VALIDATION);
        CitizenTO output = null;
        if(input != null){
            output = new CitizenTO();
            output.setName(WordUtils.capitalize(StringUtils.lowerCase(StringUtils.normalizeSpace(input.getName()))));
            output.setSurname(WordUtils.capitalize(StringUtils.lowerCase(StringUtils.normalizeSpace(input.getSurname()))));
            output.setNickname(input.getNickname());
            output.setEmail(input.getEmail());
            output.setPassword(input.getPassword());
            output.setCountry(WordUtils.capitalize(StringUtils.lowerCase(StringUtils.normalizeSpace(input.getCountry()))));
        }
        return output;
    }

}
