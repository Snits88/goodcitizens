package com.goodcitizens.utils;

public interface ErrorMsg {

    String CITIZEN_NOT_FOUND_MSG = "Citizen not Found";
    String CITIZEN_NOT_FOUND_CODE = "100-000-000";

    String FILTER_NULL_MSG = "Search Filter cannot be null";
    String FILTER_NULL_CODE = "000-000-001";

    String OBJECT_NULL_MSG = "An object must be supplied";
    String OBJECT_NULL_CODE = "000-000-002";

    String FIELDS_MANDATORY_MSG = "all Field are mandatory";
    String FIELDS_MANDATORY_CODE = "000-000-003";

    String FIELDS_LENGTH_MSG = "Each field must be have a value between 1 and 50";
    String FIELDS_LENGTH_CODE = "000-000-004";

    String INVALID_MAIL_MSG = "Invalid email format";
    String INVALID_MAIL_CODE = "000-000-005";

    String INVALID_CODE_MSG = "code invalid. It must be a valid number";
    String INVALID_CODE_CODE = "000-000-006";

    String NICKNAME_EMAIL_CREATE_MSG = "Nickname or Email already in use";
    String NICKNAME_EMAIL_CREATE_CODE = "000-000-008";

    String NICKNAME_EMAIL_UPDATE_MSG = "Nickname or Email cannot be changed because already in use";
    String NICKNAME_EMAIL_UPDATE_CODE = "000-000-008";
}
