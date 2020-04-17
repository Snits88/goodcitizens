package com.goodcitizens.service.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.exception.CitizenNotFoundExpection;
import com.goodcitizens.persistence.api.CitizenPersistenceApi;
import com.goodcitizens.service.ReadCitizensListService;
import com.goodcitizens.service.UpdateCitizenService;
import com.goodcitizens.service.businessrules.CreateUpdateCitizenFieldsBR;
import com.goodcitizens.service.businessrules.NicknameEmailUpdateBR;
import com.goodcitizens.service.businessrules.NormalizeInputFieldBR;
import com.goodcitizens.service.producer.UpdateCitizenProducer;
import com.goodcitizens.to.CitizenFilterTO;
import com.goodcitizens.to.CitizenListTO;
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
@Component("updateCitizenService")
public class UpdateCitizenServiceImpl implements UpdateCitizenService {

    final static Logger logger = Logger.getLogger(UpdateCitizenServiceImpl.class);

    @Autowired
    private ReadCitizensListService readCitizensListService;

    @Autowired
    private CreateUpdateCitizenFieldsBR createUpdateCitizenFieldsBR;

    @Autowired
    private NicknameEmailUpdateBR nicknameEmailUpdateBR;

    @Autowired
    private NormalizeInputFieldBR normalizeInputFieldBR;

    @Autowired
    private CitizenPersistenceApi citizenPersistenceApi;

    @Autowired
    private UpdateCitizenProducer updateCitizenProducer;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
    public CitizenTO update(String code, CitizenTO input) {
        codeValidation(code);
        LogUtils.logInfo(logger, LogLevel.BUSINESS, LogUtilMsg.BUSINESS_UPDATE);
        LogUtils.logDebug(logger, LogLevel.BUSINESS, code);
        createUpdateCitizenFieldsBR.validateCitizenTO(input);
        CitizenTO search = citizenPersistenceApi.readByCode(code);
        if(search == null) {
            throw new CitizenNotFoundExpection(ErrorMsg.CITIZEN_NOT_FOUND_MSG, ErrorMsg.CITIZEN_NOT_FOUND_CODE);
        }
        CitizenFilterTO searchFilter = createCitizenFilterTO(input);
        CitizenListTO searchResult = readCitizensListService.readList(searchFilter);
        Long c = Long.valueOf(StringUtils.trim(code));
        nicknameEmailUpdateBR.validUpdate(c, searchResult);
        CitizenTO normalize = normalizeInputFieldBR.normalize(input);
        return citizenPersistenceApi.updateCitizen(code, normalize);
    }

    private void codeValidation(String code) {
        String trim = StringUtils.trim(code);
        if (!StringUtils.isNumeric(trim)) {
            throw new CitizenGenericExpection(code + ErrorMsg.INVALID_CODE_MSG, ErrorMsg.INVALID_CODE_CODE);
        }
    }

    private CitizenFilterTO createCitizenFilterTO(CitizenTO citizenTO) {
        CitizenFilterTO citizenFilterTO = new CitizenFilterTO();
        citizenFilterTO.setNickname(citizenTO.getNickname());
        citizenFilterTO.setEmail(citizenTO.getEmail());
        citizenFilterTO.setEnableOR(true);
        return citizenFilterTO;
    }

}
