package com.goodcitizens.service.impl;

import com.goodcitizens.converter.CitizenToCitizenTOConveter;
import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.exception.CitizenNotFoundExpection;
import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import com.goodcitizens.service.DeleteCitizenService;
import com.goodcitizens.service.producer.DeleteCitizenProducer;
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

import java.util.Optional;

@Service
@Component("deleteCitizenService")
public class DeleteCitizenServiceImpl implements DeleteCitizenService {

    final static Logger logger = Logger.getLogger(DeleteCitizenServiceImpl.class);

    @Autowired
    private CitizenBasicCrudRepository citizenRepository;

    @Autowired
    private DeleteCitizenProducer deleteCitizenProducer;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
    public void delete(String code) {
        codeValidation(code);
        LogUtils.logInfo(logger, LogLevel.BUSINESS, LogUtilMsg.BUSINESS_DELETE);
        LogUtils.logDebug(logger, LogLevel.BUSINESS, code);
        Long c = Long.valueOf(StringUtils.trim(code));
        Optional<Citizen> citizenOp = citizenRepository.findById(c);
        if(!citizenOp.isPresent()) {
            throw new CitizenNotFoundExpection(ErrorMsg.CITIZEN_NOT_FOUND_MSG, ErrorMsg.CITIZEN_NOT_FOUND_CODE);
        }
        Citizen citizen = citizenOp.get();
        citizenRepository.delete(citizen);
        deleteCitizenProducer.notify(CitizenToCitizenTOConveter.convert(citizen));
    }

    private void codeValidation(String code) {
        String trim = StringUtils.trim(code);
        if (!StringUtils.isNumeric(trim)) {
            throw new CitizenGenericExpection(code + ErrorMsg.INVALID_CODE_MSG, ErrorMsg.INVALID_CODE_CODE);
        }
    }
}
