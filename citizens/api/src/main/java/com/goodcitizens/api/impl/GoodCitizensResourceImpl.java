package com.goodcitizens.api.impl;

import com.goodcitizens.api.GoodCitizensResource;
import com.goodcitizens.service.*;
import com.goodcitizens.service.UpdateCitizenService;
import com.goodcitizens.to.CitizenFilterTO;
import com.goodcitizens.to.CitizenListTO;
import com.goodcitizens.to.CitizenTO;
import com.goodcitizens.utils.LogLevel;
import com.goodcitizens.utils.LogUtilMsg;
import com.goodcitizens.utils.LogUtils;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/goodcitizens/citizen")
public class GoodCitizensResourceImpl implements GoodCitizensResource {

    final static Logger logger = Logger.getLogger(GoodCitizensResourceImpl.class);

    @Autowired
    private ReadCitizensListService readCitizensListService;

    @Autowired
    private CreateCitizenService createCitizenService;

    @Autowired
    private UpdateCitizenService updateCitizenService;

    @Autowired
    private DeleteCitizenService deleteCitizenService;


    @Override
    @GetMapping
    public @ResponseBody CitizenListTO readCitizensList(
            @ApiParam(value = "good ciziten's name")
            @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "good ciziten's surnmane")
            @RequestParam(value = "surname", required = false) String surnmane,
            @ApiParam(value = "good ciziten's nickname")
            @RequestParam(value = "nickname", required = false) String nickname,
            @ApiParam(value = "good ciziten's email")
            @RequestParam(value = "email", required = false) String email,
            @ApiParam(value = "parameter for pagination. it is the page to display and starts from zero")
            @RequestParam(value = "offset", required = false) String offset,
            @ApiParam(value = "parameter for pagination. it defines the number of element for page")
            @RequestParam(value = "limit", required = false) String limit,
            @ApiParam(value = "parameter for ordination", allowableValues = "[name, surname, nickname]", defaultValue = "")
            @RequestParam(value = "orderBy", required = false) String orderBy,
            @ApiParam(value = "parameter for ordination", allowableValues="[true, false]", defaultValue = "false")
            @RequestParam(value = "orderAsc", required = false) boolean orderAsc,
            @ApiParam(value = "parameter for setting search in OR or AND",  allowableValues="[true, false]", defaultValue = "false")
            @RequestParam(value = "enableOR", required = false) boolean enableOR) {
        CitizenFilterTO citizenFilterTO = createCitizenFilterTO(name, surnmane, nickname, email, offset, limit, orderBy, orderAsc, enableOR);
        LogUtils.logInfo(logger, LogLevel.RESOURCE, LogUtilMsg.RESOURCE_READ);
        return readCitizensListService.readList(citizenFilterTO);
    }

    @Override
    @PostMapping
    public @ResponseBody CitizenTO createCitizen(@RequestBody CitizenTO citizenTO) {
        LogUtils.logInfo(logger, LogLevel.RESOURCE, LogUtilMsg.RESOURCE_CREATE);
        return createCitizenService.create(citizenTO);
    }

    @Override
    @PutMapping("/{code}")
    public @ResponseBody CitizenTO updateCitizen(@PathVariable("code") String code, @RequestBody CitizenTO citizenTO) {
        LogUtils.logInfo(logger, LogLevel.RESOURCE, LogUtilMsg.RESOURCE_UPDATE);
        LogUtils.logDebug(logger, LogLevel.RESOURCE, citizenTO);
        return updateCitizenService.update(code,citizenTO);
    }

    @Override
    @DeleteMapping("/{code}")
    public void deleteCitizen(@PathVariable("code") String code) {
        LogUtils.logInfo(logger, LogLevel.RESOURCE, LogUtilMsg.RESOURCE_DELETE);
        deleteCitizenService.delete(code);
    }


    private CitizenFilterTO createCitizenFilterTO(String name, String surnmane, String nickname, String email, String offset, String limit, String orderBy, boolean orderAsc, boolean enableOR) {
        CitizenFilterTO citizenFilterTO = new CitizenFilterTO();
        citizenFilterTO.setName(name);
        citizenFilterTO.setSurname(surnmane);
        citizenFilterTO.setNickname(nickname);
        citizenFilterTO.setEmail(email);
        citizenFilterTO.setOffset(offset);
        citizenFilterTO.setLimit(limit);
        citizenFilterTO.setOrderBy(orderBy);
        citizenFilterTO.setOrderAsc(orderAsc);
        citizenFilterTO.setEnableOR(enableOR);
        return citizenFilterTO;
    }

}
