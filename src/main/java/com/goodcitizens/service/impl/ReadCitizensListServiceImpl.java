package com.goodcitizens.service.impl;

import com.goodcitizens.converter.CitizenListToCitizenListTOConverter;
import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.persistence.repository.CitizenBasicCrudRepository;
import com.goodcitizens.service.ReadCitizensListService;
import com.goodcitizens.to.CitizenFilterTO;
import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.utils.ErrorMsg;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component("readCitizensListService")
public class ReadCitizensListServiceImpl implements ReadCitizensListService {

    final static Logger logger = Logger.getLogger(ReadCitizensListServiceImpl.class);

    @Autowired
    private CitizenBasicCrudRepository citizenRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
    public CitizenListTO readList(CitizenFilterTO searchFilter) {
        filterValidation(searchFilter);
        LogUtils.logInfo(logger, LogLevel.BUSINESS, LogUtilMsg.BUSINESS_READ);
        LogUtils.logDebug(logger, LogLevel.BUSINESS, searchFilter);
        List<Citizen> searchResult = citizenRepository.readCitizensList(searchFilter);
        return CitizenListToCitizenListTOConverter.convert(searchResult);
    }

    private void filterValidation(CitizenFilterTO searchFilter){
        if(searchFilter == null){
            throw new CitizenGenericExpection(ErrorMsg.FILTER_NULL_MSG, ErrorMsg.FILTER_NULL_CODE);
        }
    }

}
