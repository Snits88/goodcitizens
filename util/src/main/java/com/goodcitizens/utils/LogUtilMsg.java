package com.goodcitizens.utils;

public interface LogUtilMsg {

    String CORRELATION_ID_INFO = "Retriving info correlation Id";

    String RESOURCE_READ = "read citizen list api";
    String RESOURCE_CREATE = "create citizen api";
    String RESOURCE_DELETE = "delete citizen api";
    String RESOURCE_UPDATE = "update citizen api";

    String BUSINESS_READ = "read citizen list business";
    String BUSINESS_CREATE = "create citizen business";
    String BUSINESS_DELETE = "delete citizen business";
    String BUSINESS_UPDATE = "update citizen business";

    String PERSOSTENCE_READ = "read citizen list business";
    String PERSOSTENCE_CREATE = "create citizen business";
    String PERSOSTENCE_DELETE = "delete citizen business";
    String PERSOSTENCE_UPDATE = "update citizen business";

    String NOTIFICATION_CREATE = "create citizen notification";
    String NOTIFICATION_DELETE = "delete citizen notification";
    String NOTIFICATION_UPDATE = "update citizen notification";

    String QUERY_PREPARETION = "Query Preparation";
    String QUERY_EXECUTION = "Query Execution";
    String ENABLEOR_OFF = "search in AND mode";
    String ENABLEOR_ON = "search in OR mode";

    String CONVERTER_LIST = "Conveter from Entities List to TO list";
    String CONVERTER_FROM_TO_ENTITY = "Converter from TO to Entity";
    String CONVERTER_FROM_ENTITY_TO = "Conveter from Entity to TO";
    String CONVERTER_FROM_TO_EVENT = "Conveter from TO to Event";

    String CITIZEN_VALIDATION = "Citizen Create Update Validation";
    String CITIZEN_INPUT_VALIDATION = "Citizen Fieled Normalization";
    String CITIZEN_NICKNAME_EMAIL_CREATE_VALIDATION = "Citizen Nickname and Email Crate Validation";
    String CITIZEN_NICKNAME_EMAIL_UPDATE_VALIDATION = "Citizen Nickname and Email Update Validation";
}
