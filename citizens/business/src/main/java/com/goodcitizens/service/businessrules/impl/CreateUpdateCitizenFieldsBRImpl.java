package com.goodcitizens.service.businessrules.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.service.businessrules.CreateUpdateCitizenFieldsBR;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.utils.ErrorMsg;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("createUpdateCitizenFieldsBR")
public class CreateUpdateCitizenFieldsBRImpl implements CreateUpdateCitizenFieldsBR {

    final static Logger logger = Logger.getLogger(CreateUpdateCitizenFieldsBRImpl.class);

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public void validateCitizenTO(CitizenTO citizenTO) {
        LogUtils.logDebug(logger, LogLevel.BUSINESS, LogUtilMsg.CITIZEN_VALIDATION);
        if(citizenTO == null){
            throw new CitizenGenericExpection(ErrorMsg.OBJECT_NULL_MSG, ErrorMsg.OBJECT_NULL_CODE);
        }
        if(citizenTO.getName() == null || citizenTO.getSurname() == null || citizenTO.getNickname() == null
                || citizenTO.getEmail() == null || citizenTO.getPassword() == null || citizenTO.getCountry() == null){
            throw new CitizenGenericExpection(ErrorMsg.FIELDS_MANDATORY_MSG, ErrorMsg.FIELDS_MANDATORY_CODE);
        }
        if(citizenTO.getName().length() > 50 || citizenTO.getSurname().length() > 50 || citizenTO.getNickname().length() > 50
                || citizenTO.getEmail().length() > 50 || citizenTO.getPassword().length() > 50 || citizenTO.getCountry().length() > 50){
            throw new CitizenGenericExpection(ErrorMsg.FIELDS_LENGTH_MSG, ErrorMsg.FIELDS_LENGTH_CODE);
        }
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(citizenTO.getEmail());
        if(!matcher.matches()){
            throw new CitizenGenericExpection(ErrorMsg.INVALID_MAIL_MSG,ErrorMsg.INVALID_MAIL_CODE);
        }
    }

}
