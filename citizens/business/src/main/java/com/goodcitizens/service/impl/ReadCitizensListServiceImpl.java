package com.goodcitizens.service.impl;

import com.goodcitizens.exception.CitizenGenericExpection;
import com.goodcitizens.persistence.api.CitizenPersistenceApi;
import com.goodcitizens.service.ReadCitizensListService;
import com.goodcitizens.service.config.properties.OrderingProperties;
import com.goodcitizens.service.config.properties.PaginationProperties;
import com.goodcitizens.to.CitizenFilterTO;
import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Component("readCitizensListService")
public class ReadCitizensListServiceImpl implements ReadCitizensListService {

    final static Logger logger = Logger.getLogger(ReadCitizensListServiceImpl.class);

    @Autowired
    private CitizenPersistenceApi citizenPersistenceApi;

    @Autowired
    private OrderingProperties orderingProperties;

    @Autowired
    private PaginationProperties paginationProperties;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { RuntimeException.class })
    public CitizenListTO readList(CitizenFilterTO searchFilter) {
        filterValidation(searchFilter);
        menageOrderingAndPAgination(searchFilter);
        LogUtils.logInfo(logger, LogLevel.BUSINESS, LogUtilMsg.BUSINESS_READ);
        LogUtils.logDebug(logger, LogLevel.BUSINESS, searchFilter);
        CitizenListTO result = citizenPersistenceApi.readCitizensList(searchFilter);
        Long totalItems = citizenPersistenceApi.count();
        result.setTotalItems(totalItems);
        return result;
    }

    private void filterValidation(CitizenFilterTO searchFilter){
        if(searchFilter == null){
            throw new CitizenGenericExpection(ErrorMsg.FILTER_NULL_MSG, ErrorMsg.FILTER_NULL_CODE);
        }
        if(!StringUtils.isEmpty(searchFilter.getOffset())){
            String trim = StringUtils.trim(searchFilter.getOffset());
            if (!StringUtils.isNumeric(trim) || Integer.valueOf(trim)<0) {
                throw new CitizenGenericExpection(ErrorMsg.INVALID_OFFSET_MSG, ErrorMsg.INVALID_OFFSET_CODE);
            }
        }
        if(!StringUtils.isEmpty(searchFilter.getLimit())){
            String trim = StringUtils.trim(searchFilter.getLimit());
            if (!StringUtils.isNumeric(trim) || Integer.valueOf(trim)<1) {
                throw new CitizenGenericExpection(ErrorMsg.INVALID_LIMIT_MSG, ErrorMsg.INVALID_LIMIT_CODE);
            }
        }
        if(!StringUtils.isEmpty(searchFilter.getOrderBy())){
            boolean check = false;
            for (CitizenOrderBy value: CitizenOrderBy.values()) {
                if(StringUtils.equalsIgnoreCase(searchFilter.getOrderBy(), value.getOrder())){
                    check = true;
                }
            }
            if(!check){
                throw new CitizenGenericExpection(ErrorMsg.INVALID_ORDER_BY_MSG, ErrorMsg.INVALID_ORDER_BY_CODE);
            }
        }
    }

    private void menageOrderingAndPAgination(CitizenFilterTO searchFilter){
        //Pagination
        Integer offset = paginationProperties.getOffset();
        Integer limit = paginationProperties.getLimit();
        if(StringUtils.isEmpty(searchFilter.getOffset())){
            searchFilter.setOffset(String.valueOf(offset));
        }
        if(StringUtils.isEmpty(searchFilter.getLimit())){
            searchFilter.setLimit(String.valueOf(limit));
        }
        //Ordering
        String orderBy = orderingProperties.getOrderBy();
        if(StringUtils.isEmpty(searchFilter.getOrderBy())){
            searchFilter.setOrderBy(String.valueOf(orderBy));
        }
    }
}
