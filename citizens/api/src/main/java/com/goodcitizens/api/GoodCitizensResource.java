package com.goodcitizens.api;

import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.to.ErrorTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
public interface GoodCitizensResource {

    @ApiOperation(value = "Returns all citizens", nickname = "readCitizensList", notes = "Query parameters are totally optional", response = CitizenListTO.class, responseContainer = "Custom List TO", produces = "application/json", tags = {})
    CitizenListTO readCitizensList(String name, String surname, String email, String country, String offset, String limit, String orderBy, boolean orderAsc, boolean enableOR);

    @ApiOperation(value = "Create a new Citizen", nickname = "createCitizen", notes = "", response = CitizenTO.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Business Rule Violated", response = ErrorTO.class)})
    CitizenTO createCitizen(CitizenTO citizenTO);

    @ApiOperation(value = "Update an existing Citizen", nickname = "updateCitizen", notes = "", response = CitizenTO.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Business Rule Violated", response = ErrorTO.class)})
    CitizenTO updateCitizen(String code, CitizenTO citizenTO);

    @ApiOperation(value = "Delete an existing Citizen", nickname = "deleteCitizen", notes = "", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Citizen Not Found", response = ErrorTO.class)})
    void deleteCitizen(String code);

}
