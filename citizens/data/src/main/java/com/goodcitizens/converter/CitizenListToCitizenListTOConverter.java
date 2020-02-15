package com.goodcitizens.converter;

import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CitizenListToCitizenListTOConverter {

    final static Logger logger = Logger.getLogger(CitizenListToCitizenListTOConverter.class);

    public static CitizenListTO convert(List<Citizen> citizens){
        LogUtils.logDebug(logger, LogLevel.BUSINESS, LogUtilMsg.CONVERTER_LIST);
        CitizenListTO citizenListTO = new CitizenListTO();
        List<CitizenTO> findOnDB = new ArrayList<>();
        citizenListTO.setTotalItems(0L);
        if(!CollectionUtils.isEmpty(citizens)){
            for (Citizen citizen : citizens) {
                CitizenTO converted = CitizenToCitizenTOConveter.convert(citizen);
                if (converted != null) {
                    findOnDB.add(converted);
                }
            }
        }
        citizenListTO.setCitizens(findOnDB);
        citizenListTO.setTotalItems(Long.valueOf(findOnDB.size()));
        return citizenListTO;
    }
}
