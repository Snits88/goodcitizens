package com.goodcitizens.service.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.exception.CitizenNotFoundExpection;
import com.goodcitizens.persistence.api.CitizenPersistenceApi;
import com.goodcitizens.service.DeleteCitizenService;
import com.goodcitizens.service.producer.DeleteCitizenProducer;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.utils.ErrorMsg;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component("deleteCitizenService")
public class DeleteCitizenServiceImpl implements DeleteCitizenService {

    final static Logger logger = Logger.getLogger(DeleteCitizenServiceImpl.class);

    @Autowired
    private CitizenPersistenceApi citizenPersistenceApi;

    @Autowired
    private DeleteCitizenProducer deleteCitizenProducer;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
    public void delete(String code) {
        codeValidation(code);
        LogUtils.logInfo(logger, LogLevel.BUSINESS, LogUtilMsg.BUSINESS_DELETE);
        LogUtils.logDebug(logger, LogLevel.BUSINESS, code);
        CitizenTO search = citizenPersistenceApi.readByCode(code);
        if(search == null) {
            throw new CitizenNotFoundExpection(ErrorMsg.CITIZEN_NOT_FOUND_MSG, ErrorMsg.CITIZEN_NOT_FOUND_CODE);
        }
        citizenPersistenceApi.deleteCitizen(code);
        deleteCitizenProducer.notify(search);
    }

    private void codeValidation(String code) {
        String trim = StringUtils.trim(code);
        if (!StringUtils.isNumeric(trim)) {
            throw new CitizenGenericExpection(code + ErrorMsg.INVALID_CODE_MSG, ErrorMsg.INVALID_CODE_CODE);
        }
    }
}
