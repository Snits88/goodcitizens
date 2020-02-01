package com.goodcitizens.persistence.repository.impl;

import com.goodcitizens.persistence.model.Citizen;
import com.goodcitizens.persistence.repository.CitizenCustomCrudRepository;
import com.goodcitizens.to.CitizenFilterTO;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class CitizenCustomCrudRepositoryImpl implements CitizenCustomCrudRepository {

    final static Logger logger = Logger.getLogger(CitizenCustomCrudRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Citizen> readCitizensList(CitizenFilterTO citizenFilterTO) {
        LogUtils.logInfo(logger, LogLevel.PERSISTENSE, LogUtilMsg.PERSOSTENCE_READ);
        LogUtils.logInfo(logger, LogLevel.PERSISTENSE, LogUtilMsg.QUERY_PREPARETION);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Citizen> cq = cb.createQuery(Citizen.class);
        Root<Citizen> root = cq.from(Citizen.class);
        Predicate[] predicates2 = createPredicates(citizenFilterTO, cb, root);
        if (!citizenFilterTO.isEnableOR()) {
            LogUtils.logInfo(logger, LogLevel.PERSISTENSE, LogUtilMsg.ENABLEOR_OFF);
            cq.select(root).where(predicates2);
        } else {
            LogUtils.logInfo(logger, LogLevel.PERSISTENSE, LogUtilMsg.ENABLEOR_ON);
            cq.select(root).where(cb.or(predicates2));
        }
        final TypedQuery<Citizen> query = entityManager.createQuery(cq);
        LogUtils.logInfo(logger, LogLevel.PERSISTENSE, LogUtilMsg.QUERY_EXECUTION);
        return query.getResultList();
    }

    private Predicate[] createPredicates(CitizenFilterTO citizenFilterTO, CriteriaBuilder cb, Root<Citizen> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (citizenFilterTO.getName() != null) {
            Expression<String> name = root.get("name");
            predicates.add(cb.equal(cb.upper(name), StringUtils.upperCase(citizenFilterTO.getName())));
        }
        if (citizenFilterTO.getSurname() != null) {
            Expression<String> surname = root.get("surname");
            predicates.add(cb.equal(cb.upper(surname), StringUtils.upperCase(citizenFilterTO.getSurname())));
        }
        if (citizenFilterTO.getNickname() != null) {
            Expression<String> nickname = root.get("nickname");
            predicates.add(cb.equal(nickname, citizenFilterTO.getNickname()));
        }
        if (citizenFilterTO.getEmail() != null) {
            Expression<String> email = root.get("email");
            predicates.add(cb.equal(email, citizenFilterTO.getEmail()));
        }
        if (citizenFilterTO.getCountry() != null) {
            Expression<String> country = root.get("country");
            predicates.add(cb.equal(cb.upper(country), StringUtils.upperCase(citizenFilterTO.getCountry())));
        }
        Predicate[] predicates2 = new Predicate[predicates.size()];
        for (int i = 0; i < predicates2.length; i++) {
            predicates2[i] = predicates.get(i);
        }
        return predicates2;
    }
}
