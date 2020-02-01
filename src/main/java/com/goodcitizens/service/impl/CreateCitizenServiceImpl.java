package com.goodcitizens.service.impl;

import com.goodcitizens.converter.CitizenTOtoCitizenConveter;
import com.goodcitizens.converter.CitizenToCitizenTOConveter;
import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import com.goodcitizens.service.CreateCitizenService;
import com.goodcitizens.service.ReadCitizensListService;
import com.goodcitizens.service.businessrules.CreateUpdateCitizenFieldsBR;
import com.goodcitizens.service.businessrules.NicknameEmailCreateBR;
import com.goodcitizens.service.businessrules.NormalizeInputFieldBR;
import com.goodcitizens.service.producer.CreateCitizenProducer;
import com.goodcitizens.to.CitizenFilterTO;
import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Component("createCitizenService")
public class CreateCitizenServiceImpl implements CreateCitizenService {

    final static Logger logger = Logger.getLogger(CreateCitizenServiceImpl.class);

    @Autowired
    private ReadCitizensListService readCitizensListService;

    @Autowired
    private CreateUpdateCitizenFieldsBR createUpdateCitizenFieldsBR;

    @Autowired
    private NicknameEmailCreateBR nicknameEmailCreateBR;

    @Autowired
    private NormalizeInputFieldBR normalizeInputFieldBR;

    @Autowired
    private CitizenBasicCrudRepository citizenRepository;

    @Autowired
    private CreateCitizenProducer createCitizenProducer;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
    public CitizenTO create(CitizenTO input) {
        createUpdateCitizenFieldsBR.validateCitizenTO(input);
        LogUtils.logInfo(logger, LogLevel.BUSINESS, LogUtilMsg.BUSINESS_READ);
        LogUtils.logDebug(logger, LogLevel.BUSINESS, input);
        CitizenFilterTO searchFilter = createCitizenFilterTO(input);
        CitizenListTO searchResult = readCitizensListService.readList(searchFilter);
        nicknameEmailCreateBR.validCreation(searchResult);
        CitizenTO normalize = normalizeInputFieldBR.normalize(input);
        Citizen toCreate = CitizenTOtoCitizenConveter.convert(normalize);
        Citizen created = citizenRepository.save(toCreate);
        CitizenTO output = CitizenToCitizenTOConveter.convert(created);
        createCitizenProducer.notify(output);
        return output;
    }

    private CitizenFilterTO createCitizenFilterTO(CitizenTO citizenTO) {
        CitizenFilterTO citizenFilterTO = new CitizenFilterTO();
        citizenFilterTO.setNickname(citizenTO.getNickname());
        citizenFilterTO.setEmail(citizenTO.getEmail());
        citizenFilterTO.setEnableOR(true);
        return citizenFilterTO;
    }
}
