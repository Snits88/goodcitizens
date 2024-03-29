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
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surnmane,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "enableOR", required = false) boolean enableOR) {
        CitizenFilterTO citizenFilterTO = createCitizenFilterTO(name, surnmane, nickname, email, enableOR);
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


    private CitizenFilterTO createCitizenFilterTO(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "surname", required = false) String surnmane, @RequestParam(value = "nickname", required = false) String nickname, @RequestParam(value = "email", required = false) String email, @RequestParam(value = "enableOR", required = false) boolean enableOR) {
        CitizenFilterTO citizenFilterTO = new CitizenFilterTO();
        citizenFilterTO.setName(name);
        citizenFilterTO.setSurname(surnmane);
        citizenFilterTO.setNickname(nickname);
        citizenFilterTO.setEmail(email);
        citizenFilterTO.setEnableOR(enableOR);
        return citizenFilterTO;
    }

}
